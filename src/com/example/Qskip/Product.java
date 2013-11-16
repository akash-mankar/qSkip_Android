package com.example.Qskip;

/**
 * Created with IntelliJ IDEA.
 * User: akash
 * Date: 11/16/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.Serializable;

public class Product implements Serializable {

    public String ownerId;
    public String productId;
    public String name;
    public String description;
    public String price;
    public boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean flag) {
        this.isActive = flag;
    }

    public String getOwnerId() {
        return ownerId;
    }
    public String getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPrice() {
        return price;
    }


}
