package com.lgf.client;

import com.lgf.springgrpc.proto.v1.HelloReply;
import com.lgf.springgrpc.proto.v1.HelloRequest;
import com.lgf.springgrpc.proto.v1.SimpleGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class SimpleGrpcClient {
    private static final Logger logger = LoggerFactory.getLogger(SimpleGrpcClient.class);

    private final ManagedChannel channel;
    private final SimpleGrpc.SimpleBlockingStub blockingStub;

    public SimpleGrpcClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.blockingStub = SimpleGrpc.newBlockingStub(channel);
        logger.info("Client initialized with server at {}:{}", host, port);
    }

    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            logger.info("Channel shutdown successfully");
        } catch (InterruptedException e) {
            logger.error("Error shutting down channel", e);
            Thread.currentThread().interrupt();
        }
    }

    public void sayHello(String name) {
        logger.info("Calling SayHello for user: {}", name);
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();

        try {
            HelloReply response = blockingStub.sayHello(request);
            logger.info("Server response: {}", response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.error("RPC failed: {}", e.getStatus(), e);
        }
    }

    public void streamHello(String name) {
        logger.info("Calling StreamHello for user: {}", name);
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();

        try {
            Iterator<HelloReply> responses = blockingStub.streamHello(request);
            logger.info("Receiving streaming responses:");

            while (responses.hasNext()) {
                HelloReply response = responses.next();
                logger.info("Server response (stream): {}", response.getMessage());
            }

            logger.info("Stream completed");
        } catch (StatusRuntimeException e) {
            logger.error("RPC failed: {}", e.getStatus(), e);
        }
    }
}
