
package de.othr.external.services.oauth.korbinianSchmidt.data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.othr.external.services.oauth.korbinianSchmidt.data package. 
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

    private final static QName _GetPersonalDataResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPersonalDataResponse");
    private final static QName _GetPrimaryPaymentMethodResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPrimaryPaymentMethodResponse");
    private final static QName _WireTransferPaymentMethod_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "wireTransferPaymentMethod");
    private final static QName _CreditCardPaymentMethod_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "creditCardPaymentMethod");
    private final static QName _GetPaymentMethodsResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPaymentMethodsResponse");
    private final static QName _GetShippingData_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getShippingData");
    private final static QName _GetShippingDataResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getShippingDataResponse");
    private final static QName _GetPersonalData_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPersonalData");
    private final static QName _GetPrimaryShippingDataResponse_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPrimaryShippingDataResponse");
    private final static QName _PaymentMethod_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "paymentMethod");
    private final static QName _GetPrimaryShippingData_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPrimaryShippingData");
    private final static QName _DataServiceException_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "DataServiceException");
    private final static QName _GetPrimaryPaymentMethod_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPrimaryPaymentMethod");
    private final static QName _GetPaymentMethods_QNAME = new QName("http://impl.service.sw.kschmidt.de/", "getPaymentMethods");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.othr.external.services.oauth.korbinianSchmidt.data
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetShippingDataResponse }
     * 
     */
    public GetShippingDataResponse createGetShippingDataResponse() {
        return new GetShippingDataResponse();
    }

    /**
     * Create an instance of {@link GetShippingData }
     * 
     */
    public GetShippingData createGetShippingData() {
        return new GetShippingData();
    }

    /**
     * Create an instance of {@link CreditCardPaymentMethod }
     * 
     */
    public CreditCardPaymentMethod createCreditCardPaymentMethod() {
        return new CreditCardPaymentMethod();
    }

    /**
     * Create an instance of {@link GetPaymentMethodsResponse }
     * 
     */
    public GetPaymentMethodsResponse createGetPaymentMethodsResponse() {
        return new GetPaymentMethodsResponse();
    }

    /**
     * Create an instance of {@link WireTransferPaymentMethod }
     * 
     */
    public WireTransferPaymentMethod createWireTransferPaymentMethod() {
        return new WireTransferPaymentMethod();
    }

    /**
     * Create an instance of {@link GetPrimaryPaymentMethodResponse }
     * 
     */
    public GetPrimaryPaymentMethodResponse createGetPrimaryPaymentMethodResponse() {
        return new GetPrimaryPaymentMethodResponse();
    }

    /**
     * Create an instance of {@link GetPersonalDataResponse }
     * 
     */
    public GetPersonalDataResponse createGetPersonalDataResponse() {
        return new GetPersonalDataResponse();
    }

    /**
     * Create an instance of {@link GetPaymentMethods }
     * 
     */
    public GetPaymentMethods createGetPaymentMethods() {
        return new GetPaymentMethods();
    }

    /**
     * Create an instance of {@link GetPrimaryPaymentMethod }
     * 
     */
    public GetPrimaryPaymentMethod createGetPrimaryPaymentMethod() {
        return new GetPrimaryPaymentMethod();
    }

    /**
     * Create an instance of {@link DataServiceException }
     * 
     */
    public DataServiceException createDataServiceException() {
        return new DataServiceException();
    }

    /**
     * Create an instance of {@link GetPrimaryShippingData }
     * 
     */
    public GetPrimaryShippingData createGetPrimaryShippingData() {
        return new GetPrimaryShippingData();
    }

    /**
     * Create an instance of {@link GetPrimaryShippingDataResponse }
     * 
     */
    public GetPrimaryShippingDataResponse createGetPrimaryShippingDataResponse() {
        return new GetPrimaryShippingDataResponse();
    }

    /**
     * Create an instance of {@link GetPersonalData }
     * 
     */
    public GetPersonalData createGetPersonalData() {
        return new GetPersonalData();
    }

    /**
     * Create an instance of {@link ShippingData }
     * 
     */
    public ShippingData createShippingData() {
        return new ShippingData();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link SessionDTO }
     * 
     */
    public SessionDTO createSessionDTO() {
        return new SessionDTO();
    }

    /**
     * Create an instance of {@link PersonalDataDTO }
     * 
     */
    public PersonalDataDTO createPersonalDataDTO() {
        return new PersonalDataDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonalDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPersonalDataResponse")
    public JAXBElement<GetPersonalDataResponse> createGetPersonalDataResponse(GetPersonalDataResponse value) {
        return new JAXBElement<GetPersonalDataResponse>(_GetPersonalDataResponse_QNAME, GetPersonalDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrimaryPaymentMethodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPrimaryPaymentMethodResponse")
    public JAXBElement<GetPrimaryPaymentMethodResponse> createGetPrimaryPaymentMethodResponse(GetPrimaryPaymentMethodResponse value) {
        return new JAXBElement<GetPrimaryPaymentMethodResponse>(_GetPrimaryPaymentMethodResponse_QNAME, GetPrimaryPaymentMethodResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WireTransferPaymentMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "wireTransferPaymentMethod")
    public JAXBElement<WireTransferPaymentMethod> createWireTransferPaymentMethod(WireTransferPaymentMethod value) {
        return new JAXBElement<WireTransferPaymentMethod>(_WireTransferPaymentMethod_QNAME, WireTransferPaymentMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardPaymentMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "creditCardPaymentMethod")
    public JAXBElement<CreditCardPaymentMethod> createCreditCardPaymentMethod(CreditCardPaymentMethod value) {
        return new JAXBElement<CreditCardPaymentMethod>(_CreditCardPaymentMethod_QNAME, CreditCardPaymentMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentMethodsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPaymentMethodsResponse")
    public JAXBElement<GetPaymentMethodsResponse> createGetPaymentMethodsResponse(GetPaymentMethodsResponse value) {
        return new JAXBElement<GetPaymentMethodsResponse>(_GetPaymentMethodsResponse_QNAME, GetPaymentMethodsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShippingData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getShippingData")
    public JAXBElement<GetShippingData> createGetShippingData(GetShippingData value) {
        return new JAXBElement<GetShippingData>(_GetShippingData_QNAME, GetShippingData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShippingDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getShippingDataResponse")
    public JAXBElement<GetShippingDataResponse> createGetShippingDataResponse(GetShippingDataResponse value) {
        return new JAXBElement<GetShippingDataResponse>(_GetShippingDataResponse_QNAME, GetShippingDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonalData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPersonalData")
    public JAXBElement<GetPersonalData> createGetPersonalData(GetPersonalData value) {
        return new JAXBElement<GetPersonalData>(_GetPersonalData_QNAME, GetPersonalData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrimaryShippingDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPrimaryShippingDataResponse")
    public JAXBElement<GetPrimaryShippingDataResponse> createGetPrimaryShippingDataResponse(GetPrimaryShippingDataResponse value) {
        return new JAXBElement<GetPrimaryShippingDataResponse>(_GetPrimaryShippingDataResponse_QNAME, GetPrimaryShippingDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "paymentMethod")
    public JAXBElement<PaymentMethod> createPaymentMethod(PaymentMethod value) {
        return new JAXBElement<PaymentMethod>(_PaymentMethod_QNAME, PaymentMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrimaryShippingData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPrimaryShippingData")
    public JAXBElement<GetPrimaryShippingData> createGetPrimaryShippingData(GetPrimaryShippingData value) {
        return new JAXBElement<GetPrimaryShippingData>(_GetPrimaryShippingData_QNAME, GetPrimaryShippingData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "DataServiceException")
    public JAXBElement<DataServiceException> createDataServiceException(DataServiceException value) {
        return new JAXBElement<DataServiceException>(_DataServiceException_QNAME, DataServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrimaryPaymentMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPrimaryPaymentMethod")
    public JAXBElement<GetPrimaryPaymentMethod> createGetPrimaryPaymentMethod(GetPrimaryPaymentMethod value) {
        return new JAXBElement<GetPrimaryPaymentMethod>(_GetPrimaryPaymentMethod_QNAME, GetPrimaryPaymentMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentMethods }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.sw.kschmidt.de/", name = "getPaymentMethods")
    public JAXBElement<GetPaymentMethods> createGetPaymentMethods(GetPaymentMethods value) {
        return new JAXBElement<GetPaymentMethods>(_GetPaymentMethods_QNAME, GetPaymentMethods.class, null, value);
    }

}
