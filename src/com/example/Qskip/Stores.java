package com.example.Qskip;

/**
 * Created with IntelliJ IDEA.
 * User: akash
 * Date: 11/16/13
 * Time: 4:49 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.Serializable;

public class Stores implements Serializable {

    public String storeId;
    public String name;
    public double latitude;
    public double longitude;
    public String imageUrl;
    public double distance;
    public long seq;

    public String getImgUrl() {
        return imageUrl;
    }

    public void setImgUrl(String url) {
        this.imageUrl = url;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }


    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String id) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 /*   public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }*/

    @Override
    public String toString() {
        return "[ Store=" + name +"]";
    }
}