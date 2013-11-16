package com.example.Qskip;

/**
 * Created with IntelliJ IDEA.
 * User: akash
 * Date: 11/16/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    public String orderId;
    public String userId;
    public double longitude;
    public double latitude;
    public boolean isActive;
    public long seq;
    public String productId;
    public Date creationDate;

}
