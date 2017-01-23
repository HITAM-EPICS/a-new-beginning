package org.hitam.epics.biswajeet.anewbeginning.support;

/**
 * Created by biswajeet on 23/12/16.
 */
public class CheckoutItem {
    private String name;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
    private float price;
    private String pictureurl;

    /*Empty Constructor for reference in Array List*/
    public CheckoutItem() {
    }

    public CheckoutItem(String name, int quantity, float price, String pictureurl) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.pictureurl = pictureurl;
    }

    public CheckoutItem(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        pictureurl =null;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getPictureurl() {
        return pictureurl;
    }
}
