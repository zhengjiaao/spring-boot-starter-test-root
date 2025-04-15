# starter-artemis


## Docker 快速启动 Artemis 服务器

安装 ActiveMQ-Artemis 环境包

```shell
docker run -d --name artemis \
  -p 61616:61616 -p 8161:8161 \
  -e ARTEMIS_USERNAME=admin \
  -e ARTEMIS_PASSWORD=admin \
  vromero/activemq-artemis
```