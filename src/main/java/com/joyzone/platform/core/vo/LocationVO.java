package com.joyzone.platform.core.vo;

public class LocationVO {

    /*
     * 经度
     */
    private double lnt;

    /*
     * 纬度
     */
    private double lat;

    /*
     * 距离
     */
    private double distance;

    public LocationVO() {
    }

    public LocationVO(double lnt, double lat) {
        this.lnt = lnt;
        this.lat = lat;
    }

    public double getLnt() {
        return lnt;
    }

    public void setLnt(double lnt) {
        this.lnt = lnt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
