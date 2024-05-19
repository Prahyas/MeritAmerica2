package com.techelevator.auctions.services;

import com.techelevator.auctions.model.Auction;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AuctionService {

    public static String API_BASE_URL = "http://localhost:3000/auctions";
    private RestTemplate restTemplate = new RestTemplate();


    public Auction[] getAllAuctions() {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_BASE_URL, Auction[].class);
        } catch (RestClientResponseException e) {
            // handles exceptions thrown by rest template and contains status codes
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            // i/o error, ex: the server isn't running
            System.out.println(e.getMessage());
        }
        return auctions;
    }

    public Auction getAuction(int id) {
        Auction auction = null;
        try {
            auction = restTemplate.getForObject(API_BASE_URL + "/" + id, Auction.class);
        } catch (RestClientResponseException e) {
            // handles exceptions thrown by rest template and contains status codes
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            // i/o error, ex: the server isn't running
            System.out.println(e.getMessage());
        }
        return auction;
    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_BASE_URL + "?title_like=" + title, Auction[].class);
        } catch (RestClientResponseException e) {
            // handles exceptions thrown by rest template and contains status codes
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            // i/o error, ex: the server isn't running
            System.out.println(e.getMessage());
        }
        return auctions;
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_BASE_URL + "?currentBid_lte=" + price, Auction[].class);
        } catch (RestClientResponseException e) {
            // handles exceptions thrown by rest template and contains status codes
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            // i/o error, ex: the server isn't running
            System.out.println(e.getMessage());
        }
        return auctions;
    }

}
