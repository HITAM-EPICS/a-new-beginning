package org.hitam.epics.biswajeet.anewbeginning.support;

/**
 * Created by biswajeet on 23/12/16.
 */
public class Item {
    private String name;
    private float price;
    private String pictureurl;

    /*Empty Constructor for reference in Array List*/
    public Item() {
    }

    /*Constructor to create item object*/
    public Item(String name, float price, String pictureurl) {
        this.name = name;
        this.price = price;
        this.pictureurl = pictureurl;
    }

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
        pictureurl = null;
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
