
package com.zja.webservices.service.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.zja.webservices.service.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetName_QNAME = new QName("http://service.webservices.zja.com/wsdl", "getName");
    private final static QName _GetNameResponse_QNAME = new QName("http://service.webservices.zja.com/wsdl", "getNameResponse");
    private final static QName _GetUser_QNAME = new QName("http://service.webservices.zja.com/wsdl", "getUser");
    private final static QName _GetUserResponse_QNAME = new QName("http://service.webservices.zja.com/wsdl", "getUserResponse");
    private final static QName _OKongOperationName_QNAME = new QName("http://service.webservices.zja.com/wsdl", "oKongOperationName");
    private final static QName _OKongOperationNameResponse_QNAME = new QName("http://service.webservices.zja.com/wsdl", "oKongOperationNameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.zja.webservices.service.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetName }
     * 
     */
    public GetName createGetName() {
        return new GetName();
    }

    /**
     * Create an instance of {@link GetNameResponse }
     * 
     */
    public GetNameResponse createGetNameResponse() {
        return new GetNameResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link OKongOperationName }
     * 
     */
    public OKongOperationName createOKongOperationName() {
        return new OKongOperationName();
    }

    /**
     * Create an instance of {@link OKongOperationNameResponse }
     * 
     */
    public OKongOperationNameResponse createOKongOperationNameResponse() {
        return new OKongOperationNameResponse();
    }

    /**
     * Create an instance of {@link UserDTO }
     * 
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.webservices.zja.com/wsdl", name = "getName")
    public JAXBElement<GetName> createGetName(GetName value) {
        return new JAXBElement<GetName>(_GetName_QNAME, GetName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.webservices.zja.com/wsdl", name = "getNameResponse")
    public JAXBElement<GetNameResponse> createGetNameResponse(GetNameResponse value) {
        return new JAXBElement<GetNameResponse>(_GetNameResponse_QNAME, GetNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.webservices.zja.com/wsdl", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.webservices.zja.com/wsdl", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OKongOperationName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OKongOperationName }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.webservices.zja.com/wsdl", name = "oKongOperationName")
    public JAXBElement<OKongOperationName> createOKongOperationName(OKongOperationName value) {
        return new JAXBElement<OKongOperationName>(_OKongOperationName_QNAME, OKongOperationName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OKongOperationNameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OKongOperationNameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.webservices.zja.com/wsdl", name = "oKongOperationNameResponse")
    public JAXBElement<OKongOperationNameResponse> createOKongOperationNameResponse(OKongOperationNameResponse value) {
        return new JAXBElement<OKongOperationNameResponse>(_OKongOperationNameResponse_QNAME, OKongOperationNameResponse.class, null, value);
    }

}
