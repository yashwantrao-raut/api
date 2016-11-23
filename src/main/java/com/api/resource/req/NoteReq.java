package com.api.resource.req;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class NoteReq {

    @NotBlank
    @Size(max = 50)
    private String title;
    private String note;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
