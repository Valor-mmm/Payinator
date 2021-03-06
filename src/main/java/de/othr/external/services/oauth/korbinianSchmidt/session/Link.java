
package de.othr.external.services.oauth.korbinianSchmidt.session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for link complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="link">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="siteToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linkObject" type="{http://impl.service.sw.kschmidt.de/}sessionLinkDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "link", propOrder = {
    "siteToken",
    "linkObject"
})
public class Link {

    protected String siteToken;
    protected SessionLinkDTO linkObject;

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
     * Gets the value of the linkObject property.
     * 
     * @return
     *     possible object is
     *     {@link SessionLinkDTO }
     *     
     */
    public SessionLinkDTO getLinkObject() {
        return linkObject;
    }

    /**
     * Sets the value of the linkObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionLinkDTO }
     *     
     */
    public void setLinkObject(SessionLinkDTO value) {
        this.linkObject = value;
    }

}
