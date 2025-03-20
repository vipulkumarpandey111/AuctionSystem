package Models;

public class Bid {
    private String id;
    private Buyer buyer;
    private Auction auction;
    private Integer bidAmount;

    public String getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Auction getAuction() {
        return auction;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

    public Bid(String id, Buyer buyer, Auction auction, Integer bidAmount) {
        this.id = id;
        this.buyer = buyer;
        this.auction = auction;
        this.bidAmount = bidAmount;
    }
}
