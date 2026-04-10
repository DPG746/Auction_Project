package com.springproject.project.dto;

public class BidRequest {

    private int bidAmount;

    private int employeeid;


    public BidRequest getBidAmount(){
        return bidAmount;
    }

    public void setBidAmount(int bidAmount){
        this.bidAmount=bidAmount;
    }
    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
}
