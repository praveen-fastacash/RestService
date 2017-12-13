
package au.com.prav.gcd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gcdSum" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gcdSum"
})
@XmlRootElement(name = "GcdSumResponse")
public class GcdSumResponse {

    protected int gcdSum;

    /**
     * Gets the value of the gcdSum property.
     * 
     */
    public int getGcdSum() {
        return gcdSum;
    }

    /**
     * Sets the value of the gcdSum property.
     * 
     */
    public void setGcdSum(int value) {
        this.gcdSum = value;
    }

}
