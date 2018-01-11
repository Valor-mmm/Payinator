
package de.othr.external.services.oauth.korbinianSchmidt.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditCardPaymentMethod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditCardPaymentMethod">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.service.sw.kschmidt.de/}paymentMethod">
 *       &lt;sequence>
 *         &lt;element name="cardProvider" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditCardPaymentMethod", propOrder = {
    "cardProvider",
    "securityCode",
    "securityNumber"
})
public class CreditCardPaymentMethod
    extends PaymentMethod
{

    protected String cardProvider;
    protected String securityCode;
    protected String securityNumber;

    /**
     * Gets the value of the cardProvider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardProvider() {
        return cardProvider;
    }

    /**
     * Sets the value of the cardProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardProvider(String value) {
        this.cardProvider = value;
    }

    /**
     * Gets the value of the securityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * Sets the value of the securityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityCode(String value) {
        this.securityCode = value;
    }

    /**
     * Gets the value of the securityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityNumber() {
        return securityNumber;
    }

    /**
     * Sets the value of the securityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityNumber(String value) {
        this.securityNumber = value;
    }

}
