package com.lgf.client;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class GrpcClientApplication {

    public static void main(String[] args) {
        log.info("Starting gRPC Client...");

        SimpleGrpcClient client = new SimpleGrpcClient("localhost", 8080);
        client.sayHello("Lucas");
        // Llamada de streaming
        //client.streamHello("Usuario Streaming");
        client.shutdown();

        log.info("gRPC Client finished.");
    }
}
