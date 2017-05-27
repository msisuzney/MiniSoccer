
package com.msisuzney.minisoccer.DQDApi.model.twins;

import java.util.List;

public class Album {

    private Integer total;
    private List<Pic> pics = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Pic> getPics() {
        return pics;
    }

    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }

}
