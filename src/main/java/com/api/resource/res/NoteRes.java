package com.api.resource.res;

public class NoteRes {
    private Long id;
    private String title;
    private String text;
    private String createTime;
    private String lastUpdateTime;

    public NoteRes(Long id, String title, String text, String createTime, String lastUpdateTime) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getId() {return id;}

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

}
