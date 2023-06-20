package com.example.systemeventsservice.services;

import com.example.systemeventsservice.models.SystemEvent;
import com.example.systemeventsservice.repositories.SystemEventRepository;
import com.example.systemeventsservice.grpc.SystemEventRequest;
import com.example.systemeventsservice.grpc.SystemEventResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import com.example.systemeventsservice.grpc.SystemEventServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@GrpcService
@Service
public class SystemEventService extends SystemEventServiceGrpc.SystemEventServiceImplBase{



    private  SystemEventRepository systemEventRepository;

    private ApplicationContext applicationContext;
    public SystemEventService(ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
        this.systemEventRepository=applicationContext.getBean(SystemEventRepository.class);
    }
    @Override
    public void log(SystemEventRequest request, StreamObserver<SystemEventResponse> responseObserver) {
        System.out.println("LOG");

        SystemEvent systemEvent = new SystemEvent();
        systemEvent.setTimestamp(request.getTimestamp());
        systemEvent.setMicroservice(request.getMicroservice());
        systemEvent.setUser(request.getUser());
        systemEvent.setAction(request.getAction());
        systemEvent.setResource(request.getResource());
        systemEvent.setResponseType(request.getResponseType());

        System.out.println(systemEvent.getTimestamp());
        System.out.println(systemEvent.getMicroservice());
        System.out.println(systemEvent.getUser());
        System.out.println(systemEvent.getAction());
        System.out.println(systemEvent.getResource());
        System.out.println(systemEvent.getResponseType());

        systemEventRepository.save(systemEvent);

        SystemEventResponse response = SystemEventResponse.newBuilder()
                .setResponsemessage("logged!")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
