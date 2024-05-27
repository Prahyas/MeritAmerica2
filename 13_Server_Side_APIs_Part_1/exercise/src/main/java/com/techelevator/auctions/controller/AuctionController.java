package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao auctionDao;

    public AuctionController() {
        this.auctionDao = new MemoryAuctionDao();
    }

    @GetMapping
    public List<Auction> list(@RequestParam(required = false, defaultValue = "") String title_like,
                              @RequestParam(required = false, defaultValue = "0") double currentBid_lte) {
        try {
            if (!title_like.isEmpty() && currentBid_lte > 0) {
                return auctionDao.getAuctionsByTitleAndMaxBid(title_like, currentBid_lte);
            } else if (!title_like.isEmpty()) {
                return auctionDao.getAuctionsByTitle(title_like);
            } else if (currentBid_lte > 0) {
                return auctionDao.getAuctionsByMaxBid(currentBid_lte);
            } else {
                return auctionDao.getAuctions();
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the search process
            System.out.println("An error occurred while searching for auctions: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list to indicate an error
        }
    }


    @GetMapping("/{id}")
    public Auction get(@PathVariable int id) {
        try {
            return auctionDao.getAuctionById(id);
        } catch (Exception e) {
            System.out.println("An error occurred while searching for auctions with ID: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Auction create(@RequestBody Auction auction) {
        try {
            return auctionDao.createAuction(auction);
        } catch (Exception e) {
            System.out.println("An error occurred while creating auctions: " + e.getMessage());
            return null;
        }
    }


}
