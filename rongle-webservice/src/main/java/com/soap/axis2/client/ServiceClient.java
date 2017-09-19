package com.soap.axis2.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class ServiceClient {           
    public static void main(String[] args1) throws AxisFault {
        String path = "http://localhost:8123/services/sayHelloService";
        EndpointReference targetEPR = new EndpointReference(path);
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);
        QName opGetWeather = new QName("http://axis2.soap.com", "sayhello");
        Object[] response = serviceClient.invokeBlocking(opGetWeather,
                new Object[]{"zhangsan"}, new Class[]{String.class});
        System.out.println(response[0]);
    }
}
