package com.neotys.ntm.jaxrs;


import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;


@ApplicationPath("/")
public class Library extends Application {


	public Library(@Context final ServletConfig servletConfig) {
		super();
	}
}
