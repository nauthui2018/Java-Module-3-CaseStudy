package model;

import java.util.List;

public class Rank {
    private int rankID;
    private String rankName;
    private List<Customer> customers;

    public Rank() {
    }

    public Rank(String rankName) {
        this.rankName = rankName;
    }

    public Rank(int rankID, String rankName) {
        this.rankID = rankID;
        this.rankName = rankName;
    }

    public int getRankID() {
        return rankID;
    }

    public String getRankName() {
        return rankName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setRankID(int rankID) {
        this.rankID = rankID;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
