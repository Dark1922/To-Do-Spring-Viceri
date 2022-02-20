package com.viceri.todo.core.swagger;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.viceri.todo.exceptionhandler.Problem;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {

		var typeResolver = new TypeResolver();

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.viceri.todo"))
				.build()
				.groupName("V1")
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.apiInfo(apiInfo())
				.tags(new Tag("Tasks", "Gerencia as task"), new Tag("Usuarios", "Gerencia os Usuários"));

	}

	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder().code(comoString(HttpStatus.INTERNAL_SERVER_ERROR))
						.description("Erro interno do Servidor").build(),
				new ResponseBuilder().code(comoString(HttpStatus.NOT_ACCEPTABLE))
						.description("Recurso não possui representação que pode ser aceita pelo consumidor").build());
	}

	private List<Response> globalPostResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder().code(comoString(HttpStatus.BAD_REQUEST))
						.description("Requisição inválida (erro do cliente)").representation(MediaType.APPLICATION_JSON)
						.apply(problemBuilder()).build(),
				new ResponseBuilder().code(comoString(HttpStatus.INTERNAL_SERVER_ERROR))
						.description("Erro interno do Servidor").build(),
				new ResponseBuilder().code(comoString(HttpStatus.NOT_ACCEPTABLE))
						.description("Recurso não possui representação que pode ser aceita pelo consumidor").build(),
				new ResponseBuilder().code(comoString(HttpStatus.UNSUPPORTED_MEDIA_TYPE))
						.description("Requisição recusada porque o corpo está em um formato não suportado")
						.representation(MediaType.APPLICATION_JSON).apply(problemBuilder()).build());
	}

	private List<Response> globalPutResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder().code(comoString(HttpStatus.BAD_REQUEST))
						.description("Requisição inválida (erro do cliente)").representation(MediaType.APPLICATION_JSON)
						.apply(problemBuilder()).build(),
				new ResponseBuilder().code(comoString(HttpStatus.INTERNAL_SERVER_ERROR))
						.description("Erro interno do Servidor").build(),
				new ResponseBuilder().code(comoString(HttpStatus.NOT_ACCEPTABLE))
						.description("Recurso não possui representação que pode ser aceita pelo consumidor").build(),
				new ResponseBuilder().code(comoString(HttpStatus.UNSUPPORTED_MEDIA_TYPE))
						.description("Requisição recusada porque o corpo está em um formato não suportado")
						.representation(MediaType.APPLICATION_JSON).apply(problemBuilder()).build());
	}

	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder().code(comoString(HttpStatus.BAD_REQUEST))
						.description("Requisição inválida (erro do cliente)").representation(MediaType.APPLICATION_JSON)
						.apply(problemBuilder()).build(),
				new ResponseBuilder().code(comoString(HttpStatus.INTERNAL_SERVER_ERROR))
						.description("Erro interno do Servidor").build());
	}

	/* Configurações para substituir métodos depreciados */
	private String comoString(HttpStatus httpStatus) {
		return String.valueOf(httpStatus.value());
	}

	private Consumer<RepresentationBuilder> problemBuilder() {
		return r -> r.model(m -> m.name("Problema").referenceModel(ref -> ref.key(
				k -> k.qualifiedModelName(q -> q.name("Problema").namespace("com.viceri.todo.exceptionhandler")))));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("ToDo-Viceri").description("API aberta para clientes e restaurantes")
				.version("1").contact(new Contact("To-Do", "https://viceri.com.br", "contato@viceri.com")).build();
	}

}