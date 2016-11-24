package com.api.resource;

import com.api.auth.UserPrincipal;
import com.api.converter.NoteToAndFromDomainConverter;
import com.api.dao.NoteDao;
import com.api.domain.Note;
import com.api.resource.req.NoteReq;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.BiPredicate;

@Path("/users/{userID}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {

   private NoteToAndFromDomainConverter  converter;
    private NoteDao noteDao;

    private BiPredicate<Long,Long> isUserValid = (principalUserId, pathParamUerId) ->principalUserId.equals(pathParamUerId);

    public NoteResource(NoteToAndFromDomainConverter converter,NoteDao noteDao) {
        this.converter = converter;
        this.noteDao=noteDao;
    }

    @DELETE
    @Path("/{noteId}")
    @UnitOfWork
    public Response delete(@Auth UserPrincipal principal,@PathParam("userID")Long userId,@PathParam("noteId") Long noteId){
        if(!isUserValid.test(principal.getUserId(),userId)){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Note note = noteDao.get(noteId).orElseThrow(NotFoundException::new);
        noteDao.delete(note);
        return Response.ok().build();
    }

    @PUT
    @Path("/{noteId}")
    @UnitOfWork
    public Response put(@Auth UserPrincipal principal,@PathParam("userID")Long userId,@PathParam("noteId") Long noteId,NoteReq noteReq){
        if(!isUserValid.test(principal.getUserId(),userId)){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Note note = noteDao.get(noteId).orElseThrow(NotFoundException::new);
        note.setText(noteReq.getNote());
        note.setTitle(noteReq.getTitle());
        note.setLastUpdateTime(DateTime.now());

        Note updatedNote = noteDao.update(note);

        return Response.ok(converter.convertFromDomain(updatedNote)).build();
    }



    @GET
    @Path("/{noteId}")
    @UnitOfWork
    public Response get(@Auth UserPrincipal principal,@PathParam("userID")Long userId, @PathParam("noteId") Long noteId){
        if(!isUserValid.test(principal.getUserId(),userId)){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Note note = noteDao.get(noteId).orElseThrow(NotFoundException::new);
        return Response.ok(converter.convertFromDomain(note)).build();
    }

    @POST
    @UnitOfWork
    public Response post(@Auth UserPrincipal principal,@PathParam("userID")Long userId, @Valid  NoteReq noteReq){
        if(!isUserValid.test(principal.getUserId(),userId)){
           return Response.status(Response.Status.FORBIDDEN).build();
        }
        Note note = noteDao.save(userId, converter.convertToDomain(noteReq));
        return Response.ok(converter.convertFromDomain(note)).status(Response.Status.CREATED).build();
    }

}
