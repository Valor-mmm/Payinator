
package de.othr.external.services.oauth.korbinianSchmidt.session;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for exceptionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="exceptionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="InvalidLoginData"/>
 *     &lt;enumeration value="InvalidSessionLink"/>
 *     &lt;enumeration value="InvalidSession"/>
 *     &lt;enumeration value="InternalFailure"/>
 *     &lt;enumeration value="NoSuchSite"/>
 *     &lt;enumeration value="CallerIpChanged"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "exceptionType")
@XmlEnum
public enum ExceptionType {

    @XmlEnumValue("InvalidLoginData")
    INVALID_LOGIN_DATA("InvalidLoginData"),
    @XmlEnumValue("InvalidSessionLink")
    INVALID_SESSION_LINK("InvalidSessionLink"),
    @XmlEnumValue("InvalidSession")
    INVALID_SESSION("InvalidSession"),
    @XmlEnumValue("InternalFailure")
    INTERNAL_FAILURE("InternalFailure"),
    @XmlEnumValue("NoSuchSite")
    NO_SUCH_SITE("NoSuchSite"),
    @XmlEnumValue("CallerIpChanged")
    CALLER_IP_CHANGED("CallerIpChanged");
    private final String value;

    ExceptionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExceptionType fromValue(String v) {
        for (ExceptionType c: ExceptionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
