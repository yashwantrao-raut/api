package com.api.resource;

import com.api.converter.NoteToAndFromDomainConverter;
import com.api.dao.NoteDao;
import com.api.domain.Note;
import com.api.resource.req.NoteReq;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{userID}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {

   private NoteToAndFromDomainConverter  converter;
    private NoteDao noteDao;

    public NoteResource(NoteToAndFromDomainConverter converter,NoteDao noteDao) {
        this.converter = converter;
        this.noteDao=noteDao;
    }



    @GET
    @Path("/{noteId}")
    @UnitOfWork
    public Response get(@PathParam("userID")Long userId,@PathParam("noteId") Long noteId){
        Note note = noteDao.get(noteId);
        return Response.ok(converter.convertFromDomain(note)).build();
    }

    @POST
    @UnitOfWork
    public Response post(@PathParam("userID")Long userId, @Valid  NoteReq noteReq){
        Note note = noteDao.save(userId, converter.convertToDomain(noteReq));
        return Response.ok(converter.convertFromDomain(note)).build();
    }

}
