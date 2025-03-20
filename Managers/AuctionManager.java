package Managers;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Models.Auction;
import Models.Bid;
import Models.Seller;
import Models.User;

public class AuctionManager {
    public HashMap <String, Auction> auctions;

    public HashMap<String, Auction> getAuctions() {
        return auctions;
    }

    private ReentrantReadWriteLock lock;
    private static AuctionManager instance;
    private SellerManager sellerManager;
    private BidManager bidManager;

    private AuctionManager(){
        auctions = new HashMap<>();
        lock = new ReentrantReadWriteLock();
        sellerManager = SellerManager.getInstance();
        bidManager = BidManager.getInstance();
    }

    public static AuctionManager getInstance(){
        if(instance == null){
            synchronized(AuctionManager.class){
                if(instance == null){
                    instance = new AuctionManager();
                }
            }
        }

        return instance;
    }

    public void CreateAuction(Scanner sc){
        lock.writeLock().lock();
        try{
            System.out.println("Add User Auction Details i.e. AuctionId, minBidLimit, maxBidLimit, participationCharge, SellerId");
            String auctionId = sc.nextLine();
            String minBidLimit = sc.nextLine();
            String maxBidLimit = sc.nextLine();
            String pCharge = sc.nextLine();
            String sellerId = sc.nextLine();

            if(sellerManager.sellers.containsKey(sellerId)){
                User seller = sellerManager.sellers.get(sellerId);
                Auction auction = new Auction(auctionId,  Integer.parseInt(minBidLimit), Integer.parseInt(maxBidLimit), Integer.parseInt(pCharge), (Seller) seller);
                auctions.put(auctionId, auction);
            }
            else{
                throw new Exception("Invalid Auction parameters");
            }
        }
        catch(Exception ex){
            System.out.println("Error in creating Auction");
        }
        finally{
            lock.writeLock().unlock();
        }
    }

    public void CloseAuction(Scanner sc){
        lock.writeLock().lock();
        try{
            System.out.println("Enter Auction Id to be closed : auctionId");
            String auctionId = sc.nextLine();
            
            if(auctions.containsKey(auctionId)){
                ShowAuctionWinner(auctionId);
                auctions.remove(auctionId);
            }
            else{
                throw new Exception("Enter correct AuctionId to close!");
            }
        }
        catch(Exception ex){
            System.out.println("Error in closing Auction");
        }
        finally{
            lock.writeLock().unlock();
        }
    }

    public void ShowAuctionWinner(String auctionId){
        lock.readLock().lock();
        try{
            Integer maxBid = 0;
            Bid bidMax = null;

            for (Bid bid : bidManager.bids.values()) {

                if(bid.getAuction().getId() == auctionId){
                    
                    if(bid.getBidAmount() > maxBid){
                        maxBid = Math.max(maxBid, bid.getBidAmount());
                        bidMax = bid;
                    }
                }
            }

            if(bidMax != null){
                System.out.println("Max Bid for the closed auction is :" + bidMax.getBidAmount() + "by Bidder : " + bidMax.getBuyer().getUserId() );
                System.out.println("Profit : " + (Math.random() * 100));
            }
            else{
                System.out.println("No winner for closed auction!");
            }
        }
        catch(Exception ex){
            System.out.println("Invalid auction Id");
        }
        finally{
            lock.readLock().unlock();
        }
    }
}
