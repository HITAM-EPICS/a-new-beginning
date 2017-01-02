package org.hitam.epics.biswajeet.anewbeginning;

/**
 * Created by biswajeet on 23/12/16.
 */
public class Item {
    private String name;
    private float price;
    private int pictureURL;

    /*Empty Constructor for reference in Array List*/
    public Item() {
    }

    /*Constructor to create item object*/
    public Item(String name, float price, int pictureURL) {
        this.name = name;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
        pictureURL = 0;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getPictureURL() {
        return pictureURL;
    }
}
