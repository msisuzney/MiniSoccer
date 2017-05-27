package com.msisuzney.minisoccer.DQDApi.model.news;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/15.
 * Time: 19:22.
 */
public class News {
    private String id;
    private String label;
    private String prev;
    private String next;
    private String max;
    private String min;
    private String page;
    private List<Article> articles = null;
    private String hotwords;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getHotwords() {
        return hotwords;
    }

    public void setHotwords(String hotwords) {
        this.hotwords = hotwords;
    }


}
