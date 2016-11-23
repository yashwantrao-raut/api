package com.api.resource;

import com.api.resource.req.NoteReq;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{userID}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {

    @POST
    public Response post(@PathParam("userID")Long userId, @Valid  NoteReq noteReq){
        return Response.ok().build();
    }

}
