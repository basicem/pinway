package com.example.systemeventsservice;

import com.example.systemeventsservice.models.SystemEvent;
import com.example.systemeventsservice.repositories.SystemEventRepository;
import com.example.systemeventsservice.services.SystemEventService;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import java.io.IOException;
import java.util.Optional;

@OpenAPIDefinition
@SpringBootApplication
public class SystemEventsServiceApplication implements CommandLineRunner, ApplicationContextAware {

	static
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SystemEventsServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Server server = ServerBuilder
				.forPort(9090)
				.addService(new SystemEventService(applicationContext))
				.build();
		try {
			server.start();
			server.awaitTermination();
		}catch (Exception e){}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
}
