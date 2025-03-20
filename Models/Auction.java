package Models;

public class Auction {
    private String id;
    private Integer minBidLimit;
    private Integer maxBidLimit;
    private Integer participationCost;
    private Seller seller;

    public Auction(String id, Integer minBidLimit, Integer maxBidLimit, Integer participationCost, Seller seller) {
        this.id = id;
        this.minBidLimit = minBidLimit;
        this.maxBidLimit = maxBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public Integer getMinBidLimit() {
        return minBidLimit;
    }

    public Integer getMaxBidLimit() {
        return maxBidLimit;
    }

    public Integer getParticipationCost() {
        return participationCost;
    }

    public Seller getSeller() {
        return seller;
    }
}
