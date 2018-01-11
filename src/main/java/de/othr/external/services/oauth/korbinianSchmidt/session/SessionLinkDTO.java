
package de.othr.external.services.oauth.korbinianSchmidt.session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sessionLinkDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sessionLinkDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="linkToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromSiteId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="toSiteId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sessionLinkDTO", propOrder = {
    "linkToken",
    "fromSiteId",
    "toSiteId",
    "userId"
})
public class SessionLinkDTO {

    protected String linkToken;
    protected long fromSiteId;
    protected long toSiteId;
    protected long userId;

    /**
     * Gets the value of the linkToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkToken() {
        return linkToken;
    }

    /**
     * Sets the value of the linkToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkToken(String value) {
        this.linkToken = value;
    }

    /**
     * Gets the value of the fromSiteId property.
     * 
     */
    public long getFromSiteId() {
        return fromSiteId;
    }

    /**
     * Sets the value of the fromSiteId property.
     * 
     */
    public void setFromSiteId(long value) {
        this.fromSiteId = value;
    }

    /**
     * Gets the value of the toSiteId property.
     * 
     */
    public long getToSiteId() {
        return toSiteId;
    }

    /**
     * Sets the value of the toSiteId property.
     * 
     */
    public void setToSiteId(long value) {
        this.toSiteId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(long value) {
        this.userId = value;
    }

}
