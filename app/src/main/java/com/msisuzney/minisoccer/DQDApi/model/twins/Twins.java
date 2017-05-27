package com.msisuzney.minisoccer.DQDApi.model.twins;

import java.util.List;

public class Twins {

    private Integer id;
    private String label;
    private String prev;
    private String next;
    private Integer page;
    private List<Feedlist> feedlist = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Feedlist> getFeedlist() {
        return feedlist;
    }

    public void setFeedlist(List<Feedlist> feedlist) {
        this.feedlist = feedlist;
    }

}
