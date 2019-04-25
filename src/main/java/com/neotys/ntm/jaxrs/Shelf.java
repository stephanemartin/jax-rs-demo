package com.neotys.ntm.jaxrs;

import com.neotys.ntm.jaxrs.data.Book;
import com.neotys.ntm.jaxrs.data.ImmutableBook;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Path("/book")
@Produces({"application/json"})
@Consumes({"application/json"})
@OpenAPIDefinition(info = @Info(title = "My Library", version = "1.0.0", description = "A sample API CRUD"))
public class Shelf {
	private static final List<Book> books = new ArrayList<>();

	static {
		books.add(ImmutableBook
				.builder()
				.title("martine")
				.description("Avanture de martine")
				.pageNumber(20)
				.build());

	}

	@GET
	@Operation(description = "get all books")
	public List<Book> getBooks() {
		return books;
	}

	@GET
	@Path("query")
	@Operation(description = "get filtered list of books")

	public List<Book> searchBooks(@DefaultValue("*") @QueryParam("title") String title,
	                              @DefaultValue("*") @QueryParam("description") String description,
	                              @DefaultValue("0") @QueryParam("page-min") int pageMin,
	                              @DefaultValue("-1") @QueryParam("page-max") int pageMax) {
		return books.stream()
				.filter(b -> match(title,b.getTitle()))
				.filter(b ->match(description,b.getDescription()))
				.filter(b -> pageNumber(pageMin,pageMax,b.getPageNumber()))
				.collect(Collectors.toList());
	}
	private boolean pageNumber(final int min,final int max,final int value){
		if(max>=0 && value>max){
			return false;
		}
		return value>min;
	}
	private boolean match(final String query,final String value){
		return "*".equals(query)|| value.contains(query);
	}
	@GET
	@Path("{id}")
	@Operation(description = "get book with id",
			responses = {
					@ApiResponse(content = @Content(schema = @Schema(implementation = Book.class))),
					@ApiResponse(responseCode = "404", description = "Book not found")

			}
	)
	public Response getBook(@Parameter(description = "ID of book to fetch") @PathParam("id") final int id) {
		if (id < books.size()) {
			return Response.ok(books.get(id)).build();
		}
		return Response.status(404).build();

	}

	@DELETE
	@Path("{id}")
	@Operation(description = "Delete book with id",
			responses = {
					@ApiResponse(responseCode = "200", description = "Book is deleted"),
					@ApiResponse(responseCode = "404", description = "Book not found")

			}
	)
	public Response removeBook(@PathParam("id") final int id) {
		if (id < books.size()) {
			books.remove(id);
			return Response.ok().build();
		}
		return Response.status(404).build();
	}

	@PUT
	@Path("{id}")
	@Operation(description = "Delete book with id",
			responses = {
					@ApiResponse(responseCode = "200", description = "Book is Updated"),
					@ApiResponse(responseCode = "404", description = "Book not found")

			}
	)
	public Response updateBook(final Book book, @PathParam("id") final int id) {
		if (id < books.size()) {
			books.set(id, book);
			return Response.ok().build();
		}
		return Response.status(404).build();
	}

	@POST
	public String addBook(final Book book) {
		books.add(book);
		JSONObject obj = new JSONObject();
		obj.put("id", books.size() - 1);
		return obj.toString();
	}

}
