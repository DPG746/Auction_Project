package com.springproject.project.service;


import com.springproject.project.Entity.Auction;
import com.springproject.project.Entity.Bid;
import com.springproject.project.Entity.Employee;
import com.springproject.project.Repository.AuctionRepository;
import com.springproject.project.Repository.BidRepository;
import com.springproject.project.Repository.EmployeeRepository;
import com.springproject.project.dto.BidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final EmployeeRepository employeeRepository;
    private final BidRepository bidRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository, EmployeeRepository employeeRepository, BidRepository bidRepository) {
        this.auctionRepository = auctionRepository;
        this.employeeRepository = employeeRepository;
        this.bidRepository = bidRepository;
    }

    public Auction createAuction(Auction auction){
        // get employee id from request
        int employeeId = auction.getEmployee().getId();

        //fetch employee from db
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found with id: "+employeeId));

        //attach real employee object
        auction.setEmployee(employee);

        //Save auction
        return auctionRepository.save(auction);

    }
    public List<Auction> getAllAuctions(){
        return auctionRepository.findAll();
    }

    public Auction getAuctionById(int id) {
        return auctionRepository.findById(id).orElseThrow(() -> new RuntimeException("Auction not found with id" + id));
    }
    //Find auction

    public Auction placeBid(int auctionId, BidRequest request){

        // Find auction
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        // Extract values
        int bidAmount = request.getBidAmount().getEmployeeid();
        int employeeId = request.getEmployeeid();

        // Validate bid
        if(bidAmount <= auction.getCurrentPrice()){
            throw new RuntimeException("Bid must be higher than current price");
        }

        // Fetch employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Create bid
        Bid bid = new Bid();
        bid.setAmount(bidAmount);
        bid.setAuction(auction);
        bid.setEmployee(employee);
        bid.setTimestamp(LocalDateTime.now());

        // Save bid
        bidRepository.save(bid);

        // Update auction
        auction.setCurrentPrice(bidAmount);

        return auctionRepository.save(auction);
    }
}
