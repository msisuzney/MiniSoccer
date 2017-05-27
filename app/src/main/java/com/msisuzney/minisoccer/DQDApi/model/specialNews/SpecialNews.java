package com.msisuzney.minisoccer.DQDApi.model.specialNews;

import java.util.List;

public class SpecialNews {

    private Integer id;
    private String label;
    private Object prev;
    private Object next;
    private List<ArticleSpecial> articles = null;
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

    public Object getPrev() {
        return prev;
    }

    public void setPrev(Object prev) {
        this.prev = prev;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public List<ArticleSpecial> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleSpecial> articles) {
        this.articles = articles;
    }

}
