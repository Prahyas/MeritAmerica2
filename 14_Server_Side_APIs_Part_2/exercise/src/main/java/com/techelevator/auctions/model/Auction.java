package com.techelevator.auctions.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Auction {

    private int id;

    @NotBlank(message = "The title field must not be blank.")
    private String title;

    @NotBlank(message = "The description field must not be blank.")
    private String description;

    @NotBlank(message = "The user field must not be blank.")
    private String user;

    @Positive(message = "The currentBid field must be greater than 0.")
    private double currentBid;

    public Auction() {
    }

    public Auction(String title, String description, String user, double currentBid) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.currentBid = currentBid;
    }

    public Auction(int id, String title, String description, String user, double currentBid) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.currentBid = currentBid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public String currentBidToString() {
        return id + ": " + title + " | Current Bid: " + currentBid;
    }

    @Override
    public String toString() {
        return "Auction{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\''
                + ", user='" + user + '\'' + ", currentBid=" + currentBid + '}';
    }
}
