package com.prav.config;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prav.soap.impl.GcdListSoapImpl;
import com.prav.soap.impl.GcdSoapImpl;
import com.prav.soap.impl.GcdSumSoapImpl;

import au.com.prav.gcd.GcdService;

@Configuration
public class CxfConfig {

  @Autowired
  private Bus bus;
  
  @Autowired
  GcdSoapImpl gcdSoapImpl;
  
  @Autowired
  GcdListSoapImpl gcdListSoapImpl;
  
  @Autowired
  GcdSumSoapImpl gcdSumSoapImpl;
  
//  @Bean
//  public Endpoint endpoint() {
//    EndpointImpl endpoint = new EndpointImpl(bus, new GcdServiceSoapImpl());
//    endpoint.publish("/GcdService");
//    return endpoint;
//  }
  
  @Bean
  public Endpoint firstGcd() {
    EndpointImpl endpoint = new EndpointImpl(bus, gcdSoapImpl);
    endpoint.setWsdlLocation(GcdService.WSDL_LOCATION.toString());
    endpoint.setServiceName(GcdService.SERVICE);
    endpoint.publish("/gcd");
    return endpoint;
  }
  
  @Bean
  public Endpoint gcdList() {
    EndpointImpl endpoint = new EndpointImpl(bus, gcdListSoapImpl);
    endpoint.setWsdlLocation(GcdService.WSDL_LOCATION.toString());
    endpoint.setServiceName(GcdService.SERVICE);
    endpoint.publish("/gcdlist");
    return endpoint;
  }
  
  @Bean
  public Endpoint gcdSum() {
    EndpointImpl endpoint = new EndpointImpl(bus, gcdSumSoapImpl);
    endpoint.setWsdlLocation(GcdService.WSDL_LOCATION.toString());
    endpoint.setServiceName(GcdService.SERVICE);
    endpoint.publish("/gcdsum");
    return endpoint;
  }
}