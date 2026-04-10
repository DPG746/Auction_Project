package com.springproject.project.controller;

import com.springproject.project.Entity.Auction;
import com.springproject.project.dto.BidRequest;
import com.springproject.project.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuctionController {
    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    //create auction
    @PostMapping("/auctions")
    public Auction createAuction(@RequestBody Auction auction){
        return auctionService.createAuction(auction);

    }

    //GET sll auctions
    @GetMapping("/auctions")
    public List<Auction> getAllAuctions(){
        return auctionService.getAllAuctions();
    }

    @GetMapping("/auctions/{id}")
    public Auction getAuctionById(@PathVariable int id){
        return auctionService.getAuctionById(id);
    }
    @PostMapping("/auctions/{id}/bid")
    public Auction placeBid(@PathVariable int id, @RequestBody BidRequest request){
        return auctionService.placeBid(id, request.getBidAmount());
    }
}
