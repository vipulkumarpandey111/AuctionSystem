package Managers;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import Models.Buyer;
import Models.User;

public class BuyerManager {
    public HashMap <String, User> buyers;
    private ReentrantReadWriteLock lock;
    private static BuyerManager instance;

    public HashMap<String, User> getBuyers() {
        return buyers;
    }

    private BuyerManager(){
        buyers = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public static BuyerManager getInstance(){
        if(instance == null){
            synchronized(BuyerManager.class){
                if(instance == null){
                    instance = new BuyerManager();
                }
            }
        }

        return instance;
    }

    public void CreateBuyer(Scanner sc){
        lock.writeLock().lock();
        try{
            System.out.println("Add User Buyer Details i.e. BuyerId");
            String buyerId = sc.nextLine();
            Buyer user = new Buyer();
            user.setUser(buyerId);
            buyers.put(buyerId, user);
        }
        catch(Exception ex){
            System.out.println("Error in creating buyer!");
        }
        finally{
            lock.writeLock().unlock();
        }
    }
}
