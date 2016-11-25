package com.api.converter;

import com.api.domain.Note;
import com.api.domain.User;
import com.api.resource.req.UserReq;
import com.api.resource.res.UserRes;
import org.joda.time.DateTime;

import java.util.HashSet;

public class UserToAndFromDomainConverter {

    public User convertToDomain(UserReq userReq){
        return new User(new HashSet<Note>(),userReq.getName(),userReq.getPassword(),userReq.getEmail(),DateTime.now());
    }

    public UserRes convertFromDomain(User user){
        return  new UserRes(user.getId(),user.getName(),user.getPassword());
    }

}
