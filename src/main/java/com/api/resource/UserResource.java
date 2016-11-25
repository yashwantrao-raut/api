package com.api.resource;

import com.api.converter.UserToAndFromDomainConverter;
import com.api.dao.UserDao;
import com.api.domain.User;
import com.api.resource.req.UserReq;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDao userDao;
    private UserToAndFromDomainConverter converter;

    public UserResource(UserDao userDao,UserToAndFromDomainConverter converter) {
        this.userDao = userDao;
        this.converter=converter;
    }

    @GET
    @Path("/createdefault")
    @UnitOfWork
    public Response loadDefaultUser(){
        UserReq userReq = new UserReq();
        userReq.setName("admin");
        userReq.setPassword("password");
        userReq.setEmail("admin@api.com");
        User user = userDao.save(converter.convertToDomain(userReq));
        return Response.ok(converter.convertFromDomain(user)).status(Response.Status.CREATED).build();
    }

    @POST
    @UnitOfWork
    public Response post(UserReq userReq){
        User user = userDao.save(converter.convertToDomain(userReq));
        return Response.ok(user.getId()).status(Response.Status.CREATED).build();
    }
}
