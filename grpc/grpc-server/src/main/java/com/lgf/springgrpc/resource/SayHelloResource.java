package com.lgf.springgrpc.resource;

import com.lgf.springgrpc.proto.v1.HelloReply;
import com.lgf.springgrpc.proto.v1.HelloRequest;
import com.lgf.springgrpc.service.GrpcServerService;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloResource {

    private static Log log = LogFactory.getLog(SayHelloResource.class);

    @Autowired
    private GrpcServerService grpcServerService;

    @GetMapping("/sayHello")
    public String sayHello() {

        grpcServerService.sayHello(HelloRequest.newBuilder().setName("Lucas").build(), new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                log.info("Received: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                log.info("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                log.info("gRPC call completed.");
            }
        });

        return "Hello from Spring Boot REST endpoint!";
    }

}
