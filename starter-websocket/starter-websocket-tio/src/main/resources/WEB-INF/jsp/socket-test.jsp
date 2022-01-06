<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>socket-test</title>
    <script type="text/javascript" src="http://cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
    <script type="text/javascript">
        //var url='ws://'+window.location.host+'/webSocket/hello';
        var url = 'ws://localhost:8080/msg';
        var socket=new WebSocket(url);
        // var socket = new SockJS(url);
        socket.onopen = function () {
            console.log('Opening');
            sayHello();
        };
        //处理消息
        socket.onmessage = function (e) {
            console.log('Received message:', e.data);
            setTimeout(function () {
                sayHello()
            }, 2000);
        };
        //处理关闭事件
        socket.onclose = function () {
            console.log('Closing');
        };

        function sayHello() {
            console.log('Sending hello');
            //发送消息
            socket.send('Hello');
        }
    </script>
</head>
<body>
this is springboot
</body>
</html>
