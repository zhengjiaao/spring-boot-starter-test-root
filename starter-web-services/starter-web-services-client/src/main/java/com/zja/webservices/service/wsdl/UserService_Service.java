package com.zja.webservices.service.wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.4.5
 * 2021-10-29T15:42:49.636+08:00
 * Generated source version: 3.4.5
 *
 */
@WebServiceClient(name = "UserService",
                  wsdlLocation = "http://localhost:8080/webservice/api?wsdl",
                  targetNamespace = "http://service.webservices.zja.com/wsdl")
public class UserService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.webservices.zja.com/wsdl", "UserService");
    public final static QName UserportName = new QName("http://service.webservices.zja.com/wsdl", "UserportName");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/webservice/api?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserService_Service.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/webservice/api?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public UserService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public UserService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns UserService
     */
    @WebEndpoint(name = "UserportName")
    public UserService getUserportName() {
        return super.getPort(UserportName, UserService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserService
     */
    @WebEndpoint(name = "UserportName")
    public UserService getUserportName(WebServiceFeature... features) {
        return super.getPort(UserportName, UserService.class, features);
    }

}
