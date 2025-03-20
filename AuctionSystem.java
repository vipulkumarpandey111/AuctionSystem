import java.util.Scanner;

import Managers.AuctionSystemManager;

public class AuctionSystem{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        output("Welcome to Auction System!");
        AuctionSystemManager auctionSystemManager = AuctionSystemManager.getInstance();
        auctionSystemManager.Run(sc);
        sc.close();
    }

    public static void output(String message){
        System.out.println(message);
    }
}