package com.springproject.project.Repository;

import com.springproject.project.Entity.Auction;
import com.springproject.project.Entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid,Integer> {
}
