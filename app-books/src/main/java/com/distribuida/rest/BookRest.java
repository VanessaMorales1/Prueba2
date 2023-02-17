package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookRest {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Find Book By ID")
    @APIResponse(responseCode = "200", description = "Se ha localizado el libro", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "400", description = "No se ha localizado el libro")
    public Book findById(@Parameter(description = "Id del libro", required = true) @PathParam("id") Integer id)  {
        return bookService.findById(id);
    }

    @GET
    @Operation(summary = "Find All Books")
    @APIResponse(responseCode = "200", description = "Listar todos los libros", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Book.class)))
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @POST
    @Operation(description = "Create Book")
    @APIResponse(responseCode = "201", description = "Se ha creado el libro")
    @APIResponse(responseCode = "500", description = "No se ha creado el libro")
    public Response create(
            @RequestBody(description = "Libro que se va a crear", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book book){
        bookService.insert(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(description = "Update book")
    @APIResponse(responseCode = "200", description = "Se ha actualizado el libro")
    @APIResponse(responseCode = "500", description = "No se ha actualizado el libro")
    public Response update(
            @RequestBody(description = "Libro que se va a actualizar", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book book,
            @Parameter(description = "Id del libro", required = true)
            @PathParam("id") Integer id){
        bookService.update(id, book);
        return Response.status((Response.Status.OK)).build();
    }
    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete Book")
    @APIResponse(responseCode = "204", description = "Se ha eliminado el libro")
    @APIResponse(responseCode = "500", description = "No se ha eliminado el libro")
    public Response delete(
            @Parameter(description = "Id del libro", required = true)
            @PathParam("id") Integer id) {
        bookService.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }



}
