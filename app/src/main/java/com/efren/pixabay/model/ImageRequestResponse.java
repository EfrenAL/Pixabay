package com.efren.pixabay.model;

import java.util.List;

/**
 * Created by efren.lamolda on 07.08.18.
 */

public class ImageRequestResponse {

    private Integer total;
    private Integer totalHits;
    private List<Image> hits;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<Image> getHits() {
        return hits;
    }

    public void setHits(List<Image> hits) {
        this.hits = hits;
    }
}
