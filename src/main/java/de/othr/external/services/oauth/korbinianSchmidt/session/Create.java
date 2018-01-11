
package de.othr.external.services.oauth.korbinianSchmidt.session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for create complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="create">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="siteToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginData" type="{http://impl.service.sw.kschmidt.de/}loginDataDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "create", propOrder = {
    "siteToken",
    "loginData"
})
public class Create {

    protected String siteToken;
    protected LoginDataDTO loginData;

    /**
     * Gets the value of the siteToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteToken() {
        return siteToken;
    }

    /**
     * Sets the value of the siteToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteToken(String value) {
        this.siteToken = value;
    }

    /**
     * Gets the value of the loginData property.
     * 
     * @return
     *     possible object is
     *     {@link LoginDataDTO }
     *     
     */
    public LoginDataDTO getLoginData() {
        return loginData;
    }

    /**
     * Sets the value of the loginData property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginDataDTO }
     *     
     */
    public void setLoginData(LoginDataDTO value) {
        this.loginData = value;
    }

}
