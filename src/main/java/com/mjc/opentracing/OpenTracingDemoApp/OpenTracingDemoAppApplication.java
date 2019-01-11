package com.mjc.opentracing.OpenTracingDemoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;

@SpringBootApplication
public class OpenTracingDemoAppApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(OpenTracingDemoAppApplication.class, args);
	}

	
	// sudo docker run --rm -it --net=host jaegertracing/all-in-one
	@Bean
	public io.opentracing.Tracer jaegerTracer() {
		return new Configuration("spring-boot", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
				new Configuration.ReporterConfiguration())
				.getTracer();
	} 
	
	/*
	@Bean
	public io.opentracing.Tracer zipkinTracer() {
	    OkHttpSender okHttpSender = OkHttpSender.create("http://localhost:9411/api/v1/spans");
	    AsyncReporter<Span> reporter = AsyncReporter.builder(okHttpSender).build();
	    Tracing braveTracer = Tracing.newBuilder().localServiceName("spring-boot").reporter(reporter).build();
	    return BraveTracer.create(braveTracer);
	} */
	
}

