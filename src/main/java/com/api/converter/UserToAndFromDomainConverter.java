package com.api.converter;

import com.api.domain.Note;
import com.api.domain.User;
import com.api.resource.req.NoteReq;
import com.api.resource.req.UserReq;
import com.api.resource.res.NoteRes;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashSet;

public class UserToAndFromDomainConverter {

    public User convertToDomain(UserReq userReq){
        return new User(new HashSet<Note>(),userReq.getName(),userReq.getPassword(),userReq.getEmail(),DateTime.now());
    }

}
