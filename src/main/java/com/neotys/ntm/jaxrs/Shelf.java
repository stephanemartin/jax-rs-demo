package com.neotys.ntm.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/hello")
@Produces({"text/html"})
public class Shelf {

	@GET
	public String hello() {
		return "<HTML><H1>Hello JAX-RS</H1></HTML>";
	}
}
