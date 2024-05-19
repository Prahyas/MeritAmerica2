package com.techelevator.auctions.services;

import com.techelevator.auctions.model.Auction;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AuctionService {

    public static String API_BASE_URL = "http://localhost:3000/auctions";
    private RestTemplate restTemplate = new RestTemplate();


    public Auction add(Auction newAuction) {
        Auction auctionToAdd = null;
        try {
            newAuction.setTitle("Beautiful Portrait");
            newAuction.setDescription("Expensive portrait of a beautiful woman.");
            newAuction.setUser("user@user.com");
            newAuction.setCurrentBid(100.00);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Auction> entity = new HttpEntity<>(newAuction, headers);
            auctionToAdd = restTemplate.postForObject(API_BASE_URL, entity, Auction.class);

        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());

        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode());

        }
        return auctionToAdd;
    }

    public boolean update(Auction updatedAuction) {
        Boolean updateStatus = false;
        try {
            updatedAuction.setTitle("Beautiful Portrait");
            updatedAuction.setDescription("Expensive portrait of a beautiful woman.");
            updatedAuction.setUser("user@user.com");
            updatedAuction.setCurrentBid(100.00);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Auction> entity = new HttpEntity<>(updatedAuction, headers);
            restTemplate.put(API_BASE_URL + "/" + updatedAuction.getId(), entity, Auction.class);

            updateStatus = true;


        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());

        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode());

        }
        return updateStatus;
    }

    public boolean delete(int auctionId) {
        Boolean deleteStatus = false;
        try {
            restTemplate.delete(API_BASE_URL + "/" + auctionId, Auction.class);
            deleteStatus = true;
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());

        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode());

        }
        return deleteStatus;
    }

    public Auction[] getAllAuctions() {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_BASE_URL, Auction[].class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auctions;
    }

    public Auction getAuction(int id) {
        Auction auction = null;
        try {
            auction = restTemplate.getForObject(API_BASE_URL + "/" + id, Auction.class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auction;
    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_BASE_URL + "?title_like=" + title, Auction[].class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auctions;
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_BASE_URL + "?currentBid_lte=" + price, Auction[].class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auctions;
    }

    private HttpEntity<Auction> makeEntity(Auction auction) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(auction, headers);
    }

}
