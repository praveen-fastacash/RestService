package au.com.prav.gcd;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2017-12-14T22:02:16.950+11:00
 * Generated source version: 3.2.0
 * 
 */
@WebService(targetNamespace = "http://www.prav.com.au/gcd/", name = "GcdPort")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface GcdPort {

    @WebMethod(operationName = "RetrieveFirst", action = "http://www.prav.com.au/gcd/RetrieveFirst")
    @WebResult(name = "RetreiveFirstResponse", targetNamespace = "http://www.prav.com.au/gcd/", partName = "parameters")
    public RetreiveFirstResponse retrieveFirst(
        @WebParam(partName = "parameters", name = "GcdRequest", targetNamespace = "http://www.prav.com.au/gcd/")
        GcdRequest parameters
    );
}
