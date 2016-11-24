package com.api.dao;

import com.api.domain.Note;
import com.api.domain.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

public class NoteDao extends AbstractDAO<Note>{

    public NoteDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Note save(Long userId,Note note){
        note.setUser(currentSession().load(User.class, userId));
        return super.persist(note);
    }

    public Optional<Note> get(Long noteId){
        return Optional.ofNullable(super.get(noteId));
    }

    public Note update(Note note){
        return super.persist(note);
    }

    public void delete(Note note) {
        currentSession().delete(note);
    }
}
