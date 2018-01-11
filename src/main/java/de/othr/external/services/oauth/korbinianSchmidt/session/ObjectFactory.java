
package de.othr.external.services.oauth.korbinianSchmidt.session;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.othr.external.services.oauth.korbinianSchmidt.session package. 
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

    private final static QName _PrepareLink_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "prepareLink");
    private final static QName _Validate_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "validate");
    private final static QName _SessionServiceException_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "SessionServiceException");
    private final static QName _End_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "end");
    private final static QName _Create_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "create");
    private final static QName _CreateResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "createResponse");
    private final static QName _EndResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "endResponse");
    private final static QName _Link_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "link");
    private final static QName _PrepareLinkResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "prepareLinkResponse");
    private final static QName _ValidateResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "validateResponse");
    private final static QName _LinkResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "linkResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.othr.external.services.oauth.korbinianSchmidt.session
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LinkResponse }
     * 
     */
    public LinkResponse createLinkResponse() {
        return new LinkResponse();
    }

    /**
     * Create an instance of {@link ValidateResponse }
     * 
     */
    public ValidateResponse createValidateResponse() {
        return new ValidateResponse();
    }

    /**
     * Create an instance of {@link PrepareLinkResponse }
     * 
     */
    public PrepareLinkResponse createPrepareLinkResponse() {
        return new PrepareLinkResponse();
    }

    /**
     * Create an instance of {@link Link }
     * 
     */
    public Link createLink() {
        return new Link();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link EndResponse }
     * 
     */
    public EndResponse createEndResponse() {
        return new EndResponse();
    }

    /**
     * Create an instance of {@link End }
     * 
     */
    public End createEnd() {
        return new End();
    }

    /**
     * Create an instance of {@link PrepareLink }
     * 
     */
    public PrepareLink createPrepareLink() {
        return new PrepareLink();
    }

    /**
     * Create an instance of {@link Validate }
     * 
     */
    public Validate createValidate() {
        return new Validate();
    }

    /**
     * Create an instance of {@link SessionServiceException }
     * 
     */
    public SessionServiceException createSessionServiceException() {
        return new SessionServiceException();
    }

    /**
     * Create an instance of {@link SessionLinkDTO }
     * 
     */
    public SessionLinkDTO createSessionLinkDTO() {
        return new SessionLinkDTO();
    }

    /**
     * Create an instance of {@link SessionDTO }
     * 
     */
    public SessionDTO createSessionDTO() {
        return new SessionDTO();
    }

    /**
     * Create an instance of {@link LoginDataDTO }
     * 
     */
    public LoginDataDTO createLoginDataDTO() {
        return new LoginDataDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrepareLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "prepareLink")
    public JAXBElement<PrepareLink> createPrepareLink(PrepareLink value) {
        return new JAXBElement<PrepareLink>(_PrepareLink_QNAME, PrepareLink.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Validate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "validate")
    public JAXBElement<Validate> createValidate(Validate value) {
        return new JAXBElement<Validate>(_Validate_QNAME, Validate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "SessionServiceException")
    public JAXBElement<SessionServiceException> createSessionServiceException(SessionServiceException value) {
        return new JAXBElement<SessionServiceException>(_SessionServiceException_QNAME, SessionServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link End }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "end")
    public JAXBElement<End> createEnd(End value) {
        return new JAXBElement<End>(_End_QNAME, End.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "endResponse")
    public JAXBElement<EndResponse> createEndResponse(EndResponse value) {
        return new JAXBElement<EndResponse>(_EndResponse_QNAME, EndResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Link }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "link")
    public JAXBElement<Link> createLink(Link value) {
        return new JAXBElement<Link>(_Link_QNAME, Link.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrepareLinkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "prepareLinkResponse")
    public JAXBElement<PrepareLinkResponse> createPrepareLinkResponse(PrepareLinkResponse value) {
        return new JAXBElement<PrepareLinkResponse>(_PrepareLinkResponse_QNAME, PrepareLinkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "validateResponse")
    public JAXBElement<ValidateResponse> createValidateResponse(ValidateResponse value) {
        return new JAXBElement<ValidateResponse>(_ValidateResponse_QNAME, ValidateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "linkResponse")
    public JAXBElement<LinkResponse> createLinkResponse(LinkResponse value) {
        return new JAXBElement<LinkResponse>(_LinkResponse_QNAME, LinkResponse.class, null, value);
    }

}
