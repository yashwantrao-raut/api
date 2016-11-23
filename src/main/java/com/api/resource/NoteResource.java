package com.api.resource;

import com.api.resource.req.NoteReq;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users/{userID}/notes")
public class NoteResource {

    @POST
    public Response post(@PathParam("userID")Long userId, NoteReq noteReq){
        return Response.ok().build();
    }

}
