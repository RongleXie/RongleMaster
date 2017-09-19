package com.soap.axis2;

public class ServiceServerImpl implements IServiceServer {
    public String sayhello(String name) {
        return "my name is " + name;
    }
}
