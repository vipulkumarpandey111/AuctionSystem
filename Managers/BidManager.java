package Managers;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Models.Auction;
import Models.Bid;
import Models.Buyer;
import Models.User;

public class BidManager {
    public HashMap <String, Bid> bids;
    private ReentrantReadWriteLock lock;
    private static BidManager instance;
    private BuyerManager buyerManager;
    private AuctionManager auctionManager;
    

    private BidManager(){
        buyerManager = BuyerManager.getInstance();
        bids = new HashMap<>();
        lock = new ReentrantReadWriteLock();
        auctionManager = AuctionManager.getInstance();
    }

    public static BidManager getInstance(){
        if(instance == null){
            synchronized(BidManager.class){
                if(instance == null){
                    instance = new BidManager();
                }
            }
        }

        return instance;
    }

    public void CreateBid(String bidId, String buyerId, String auctionId, Integer amount){
        lock.writeLock().lock();
        try{
            User buyer;
            Auction auction;
            if(buyerManager.buyers.containsKey(buyerId)){
                buyer = buyerManager.buyers.get(buyerId);
            }
            else{
                throw new Exception("Incorrect buyer Id");
            }

            if(auctionManager.auctions.containsKey(auctionId)){
                auction = auctionManager.auctions.get(auctionId);
            }
            else{
                throw new Exception("Incorrect AuctionId");
            }
            
            Bid bid = new Bid(bidId, (Buyer) buyer, auction, amount);
            bids.put(bidId, bid);
        }
        catch(Exception ex){
            System.out.println("Error in creating Bid!");
        }
        finally{
            lock.writeLock().unlock();
        }
    }

    public void UpdateBid(String buyerId, String auctionId, Integer amount){
        lock.writeLock().lock();
        try{
            if(buyerManager.buyers.containsKey(buyerId) && auctionManager.auctions.containsKey(auctionId)){

                for (Bid bid : bids.values()){

                    if(bid.getBuyer().getUserId() == buyerId && bid.getAuction().getId() == auctionId){
                        String bidId = bid.getId();
                        Bid newBid = new Bid(bidId, bid.getBuyer(), bid.getAuction(), amount);
                        bids.put(bidId, newBid);
                    }
                }
            }
            else{
                throw new Exception("Incorrect buyerId / auctionId");
            }
        }
        catch(Exception ex){
            System.out.println("Error in creating Bid!");
        }
        finally{
            lock.writeLock().unlock();
        }
    }

    public void AddBid(Scanner sc){
        try{
            System.out.println("Add User Bid Details : bidId, buyerId, auctionId, amount");
            CreateBid(sc.nextLine(), sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()));
        }
        catch (Exception ex){
            System.out.println("Invalid Bid details input format!");
        }
    }

    public void UpdateExistingBid(Scanner sc){
        try{
            System.out.println("Update User Bid Details : buyerId, auctionId, amount");
            UpdateBid(sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()));
        }
        catch (Exception ex){
            System.out.println("Invalid Update Bid details input format!");
        }
    }
}
