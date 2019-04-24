package com.neotys.ntm.jaxrs;

import com.neotys.ntm.jaxrs.data.Book;
import com.neotys.ntm.jaxrs.data.ImmutableBook;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api")
@Produces({"application/json"})
public class Shelf {

	private static final List<Book> bookList=new ArrayList<>();

	@GET
	@Path("/books")
	public Response getBooks() {
		return Response.ok(bookList).build();
	}



	@POST
	public Response addBook(final Book book){
		System.out.println(book);
		return Response.ok().build();
	}
	@GET
	public String getHello() {
		return "Cuisine et moi / Java 18";
	}

}
