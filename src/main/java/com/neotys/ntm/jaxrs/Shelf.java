package com.neotys.ntm.jaxrs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.glassfish.jersey.model.internal.RankedComparator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/hello")
@Produces({"text/html"})
@OpenAPIDefinition(info = @Info(title = "Hello", version = "1.0.0", description = "A sample API"))
public class Shelf {

	@GET
	@Operation(
			summary = "Say Hello",
			description = "Hello"
	)
	public String hello() {
		return "<HTML><H1>Hello JAX-RS</H1></HTML>";
	}
}
