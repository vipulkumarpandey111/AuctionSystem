package Managers;
import java.util.Scanner;

public class AuctionSystemManager {
    private static AuctionSystemManager instance;
    private BuyerManager buyerManager;
    private AuctionManager auctionManager;
    private SellerManager sellerManager;
    private BidManager bidManager;
    

    private AuctionSystemManager(){
        buyerManager = BuyerManager.getInstance();
        auctionManager = AuctionManager.getInstance();
        sellerManager = SellerManager.getInstance();
        bidManager = BidManager.getInstance();
    }

    public static AuctionSystemManager getInstance(){
        if(instance == null){
            synchronized(AuctionSystemManager.class){
                if(instance == null){
                    instance = new AuctionSystemManager();
                }
            }
        }

        return instance;
    }

    public void Run(Scanner sc){
        boolean exit = false;

        while(!exit){
            String command = sc.nextLine();

            switch (command) {
                case "ADD_BUYER":
                    buyerManager.CreateBuyer(sc);
                    break;
                case "ADD_SELLER":
                    sellerManager.CreateSeller(sc);
                    break;
                case "CREATE_AUCTION":
                    auctionManager.CreateAuction(sc);
                    break;
                case "CLOSE_AUCTION":
                    auctionManager.CloseAuction(sc);
                    break;
                case "CREATE_BID":
                    bidManager.AddBid(sc);
                    break;
                case "UPDATE_BID":
                    bidManager.UpdateExistingBid(sc);
                    break;
                case "EXIT":
                    exit = true;
                    break;
                default:
                    output("Enter a valid command!");
                    break;
            }
        }
    }

    public void output(String message){
        System.out.println(message);
    }
}
