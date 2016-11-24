package com.api.resource;

import com.api.converter.UserToAndFromDomainConverter;
import com.api.dao.UserDao;
import com.api.domain.User;
import com.api.resource.req.UserReq;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @POST
    @UnitOfWork
    public Response post(UserReq userReq){
        User user = userDao.save(converter.convertToDomain(userReq));
        return Response.ok(user.getId()).status(Response.Status.CREATED).build();
    }
}
