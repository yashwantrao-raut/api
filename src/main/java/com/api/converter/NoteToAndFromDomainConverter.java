package com.api.converter;

import com.api.domain.Note;
import com.api.resource.req.NoteReq;
import com.api.resource.res.NoteRes;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class NoteToAndFromDomainConverter {

    public Note convertToDomain(NoteReq noteReq){
        return new Note(noteReq.getTitle(),noteReq.getNote(), DateTime.now(),DateTime.now());
    }

    public NoteRes convertFromDomain(Note note){
        DateTimeFormatter formater = DateTimeFormat.forPattern("MMM dd yyyy HH:mm:ss");
        String createTime = formater.print(note.getCreateTime());
        String lastUpdateTime = formater.print(note.getLastUpdateTime());
        return new NoteRes(note.getId(),note.getText(),note.getTitle(),createTime,lastUpdateTime);
    }
}
