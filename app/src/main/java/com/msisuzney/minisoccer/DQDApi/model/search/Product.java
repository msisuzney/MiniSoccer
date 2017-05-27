
package com.msisuzney.minisoccer.DQDApi.model.search;

import java.util.List;

public class Product {

    private String product_code;
    private Integer type_id;
    private String img_url;
    private String title;
    private String sale_price;
    private String list_price;
    private Integer postage;
    private Boolean is_haitao;
    private Boolean is_buyable;
    private Integer sales;
    private Status status;
    private List<Tag> tags = null;
    private String label;
    private List<Label> labels = null;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getList_price() {
        return list_price;
    }

    public void setList_price(String list_price) {
        this.list_price = list_price;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Boolean getIs_haitao() {
        return is_haitao;
    }

    public void setIs_haitao(Boolean is_haitao) {
        this.is_haitao = is_haitao;
    }

    public Boolean getIs_buyable() {
        return is_buyable;
    }

    public void setIs_buyable(Boolean is_buyable) {
        this.is_buyable = is_buyable;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

}
