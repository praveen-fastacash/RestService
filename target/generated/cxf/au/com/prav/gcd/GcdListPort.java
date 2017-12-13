package au.com.prav.gcd;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2017-12-14T08:52:33.035+11:00
 * Generated source version: 3.2.0
 * 
 */
@WebService(targetNamespace = "http://www.prav.com.au/gcd/", name = "GcdListPort")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface GcdListPort {

    @WebMethod(operationName = "GcdList", action = "http://www.prav.com.au/gcd/GcdList")
    @WebResult(name = "GcdListResponse", targetNamespace = "http://www.prav.com.au/gcd/", partName = "parameters")
    public GcdListResponse gcdList(
        @WebParam(partName = "parameters", name = "GcdRequest", targetNamespace = "http://www.prav.com.au/gcd/")
        GcdRequest parameters
    );
}
