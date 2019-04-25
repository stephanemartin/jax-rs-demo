package com.neotys.ntm.jaxrs;


import com.neotys.ntm.jaxrs.data.Book;
import com.neotys.ntm.jaxrs.data.ImmutableBook;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.*;

public class ShelfTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(Shelf.class);
	}

	@Test
	public void getBooks() {
		Response response = target("/book/")
				.request()
				.get();
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),
				response.getStatus());
		String content = response.readEntity(String.class);
		assertEquals("Content of response is: ", "[{\"title\":\"martine\",\"description\":\"Avanture de martine\",\"pageNumber\":20}]", content);
	}

	@Test
	public void searchBooks() {
		ImmutableBook newBook = ImmutableBook.builder().description("toto est parti")
				.title("toto adventures")
				.pageNumber(2).build();
		Response response = target("/book")
				.request()
				.post(Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE));
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),response.getStatus());
		JSONObject jsonObject = new JSONObject(response.readEntity(String.class));
		assertEquals(1,jsonObject.get("id"));



		Response response2 = target("/book/query")
				.queryParam("title","toto")
				.request()
				.get();
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response2.getStatus());
		String content = response2.readEntity(String.class);
		assertEquals("Content of response is: ", "[{\"title\":\"toto adventures\",\"description\":\"toto est parti\",\"pageNumber\":2}]", content);
	}

	@Test
	public void getBook() {
		Response response = target("/book/0")
				.request()
				.get();
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),
				response.getStatus());
		String content = response.readEntity(String.class);
		assertEquals("Content of response is: ", "{\"title\":\"martine\",\"description\":\"Avanture de martine\",\"pageNumber\":20}", content);
	}

	@Test
	public void removeBook() {
		Response response = target("/book/0")
				.request()
				.delete();
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),response.getStatus());

		Response response2 = target("/book/")
				.request()
				.get();
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response2.getStatus());
		List content = response2.readEntity(List.class);
		assertTrue(content.isEmpty());
	}


	@Test
	public void updateBook() {
		ImmutableBook newBook = ImmutableBook.builder().description("toto est parti")
				.title("toto adventures")
				.pageNumber(2).build();
		Response response = target("/book/0")
				.request()
				.put(Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE));
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),response.getStatus());


		Response response2 = target("/book/0")
				.request()
				.get();
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),
				response2.getStatus());
		Book content = response2.readEntity(ImmutableBook.class);
		assertEquals("Content of response is: ", newBook, content);
	}

	@Test
	public void addBook() {
		ImmutableBook newBook = ImmutableBook.builder().description("toto est parti")
				.title("toto adventures")
				.pageNumber(2).build();
		Response response = target("/book")
				.request()
				.post(Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE));
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),response.getStatus());
		JSONObject jsonObject = new JSONObject(response.readEntity(String.class));
		assertEquals(1,jsonObject.get("id"));


		Response response2 = target("/book/1")
				.request()
				.get();
		assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(),
				response2.getStatus());
		Book content = response2.readEntity(ImmutableBook.class);
		assertEquals("Content of response is: ", newBook, content);
	}
}