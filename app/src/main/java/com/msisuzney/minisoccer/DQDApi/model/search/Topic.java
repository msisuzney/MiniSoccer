
package com.msisuzney.minisoccer.DQDApi.model.search;

import java.util.List;

public class Topic {

    private String id;
    private String description;
    private String group_title;
    private String created_at;
    private Integer attachments_total;
    private List<Attachment> attachments = null;
    private List<Object> videos = null;
    private Author author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getAttachments_total() {
        return attachments_total;
    }

    public void setAttachments_total(Integer attachments_total) {
        this.attachments_total = attachments_total;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<Object> getVideos() {
        return videos;
    }

    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
