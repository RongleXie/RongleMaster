import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;

/**
 * @author wxt.xqr
 * @version 2017-8-30
 */
public class Test {

    @org.junit.Test
    public void sayHello() throws AxisFault {
        String path = "http://localhost:8123/services/sayHelloService";
        EndpointReference targetEPR = new EndpointReference(path);
        RPCServiceClient rpcServiceClient = new RPCServiceClient();
        Options options = rpcServiceClient.getOptions();
        options.setTo(targetEPR);
        options.setTimeOutInMilliSeconds(60*1000);
        QName opGetWeather = new QName("http://axis2.soap.com", "sayhello");
        Object[] response = rpcServiceClient.invokeBlocking(opGetWeather,
                new Object[]{"zhangsan"}, new Class[]{String.class});
        System.out.println(response[0]);
    }
}
