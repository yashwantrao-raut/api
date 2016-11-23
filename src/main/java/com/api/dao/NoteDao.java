package com.api.dao;

import com.api.domain.Note;
import com.api.domain.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class NoteDao extends AbstractDAO<Note>{

    public NoteDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Note save(Long userId,Note note){
        note.setUser(currentSession().load(User.class, userId));
        return super.persist(note);
    }
}
