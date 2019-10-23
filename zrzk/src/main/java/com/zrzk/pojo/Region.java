package com.zrzk.pojo;
//正常区间表
public class Region {
    private Integer genreId;
    //最小值
    private Double small;
    //最大值
    private Double big;

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Double getSmall() {
        return small;
    }

    public void setSmall(Double small) {
        this.small = small;
    }

    public Double getBig() {
        return big;
    }

    public void setBig(Double big) {
        this.big = big;
    }

    @Override
    public String toString() {
        return "Region{" +
                "genreId=" + genreId +
                ", small=" + small +
                ", big=" + big +
                '}';
    }
}
