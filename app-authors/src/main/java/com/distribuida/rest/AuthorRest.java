package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.service.AuthorService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {
    @Inject
    private AuthorService authorService;


    @GET
    @Path("/{id}")
    @Operation(summary = "Find Author by ID")
    @APIResponse(responseCode = "200", description = "Se ha localizado al autor", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @APIResponse(responseCode = "400", description = "No se ha localizado al autor")
    public Author findById(@Parameter(description = "Id del autor", required = true) @PathParam("id") Long id) {
        return authorService.findById(id);
    }
    @GET
    @Operation(summary = "Find All Authors")
    @APIResponse(responseCode = "200", description = "Listar todo los autores", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Author.class)))
    public List<Author> findAll () {
        return authorService.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Create Author")
    @APIResponse(responseCode = "201", description = "Se ha creado el autor")
    @APIResponse(responseCode = "500", description = "No se ha creado el autor")
    public Response create(
            @RequestBody(description = "Autor que se va a crear", required = true,
                    content = @Content(schema = @Schema(implementation = Author.class)))
            Author author)  {
        authorService.create(author);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT @Path("/{id}")
    @Operation(description = "Update autor")
    @APIResponse(responseCode = "200", description = "Se ha actualizado el autor")
    @APIResponse(responseCode = "500", description = "No se ha actualizado el autor")
    public Response update (
            @RequestBody(description = "Autor que se va a actualizar", required = true,
                    content = @Content(schema = @Schema(implementation = Author.class)))
            Author author,
            @Parameter(description = "Id del autor", required = true)
            @PathParam("id") Long id){
        authorService.update(id,author);
        return Response.status((Response.Status.OK) ).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Author")
    @APIResponse(responseCode = "204",description = "Se ha eliminado al autor")
    public Response delete (
            @Parameter(description = "Id del Autor",required = true)
            @PathParam("id") Long id){
        authorService.delete(id);
        return Response.status((Response.Status.NO_CONTENT) ).build();
    }
}