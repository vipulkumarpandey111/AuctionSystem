package Managers;

import Models.Seller;
import Models.User;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SellerManager{
    public HashMap <String, User> sellers;
    private ReentrantReadWriteLock lock;
    private static SellerManager instance;

    private SellerManager(){
        sellers = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public static SellerManager getInstance(){
        if(instance == null){
            synchronized(SellerManager.class){
                if(instance == null){
                    instance = new SellerManager();
                }
            }
        }

        return instance;
    }

    public void CreateSeller(Scanner sc){
        lock.writeLock().lock();
        try{
            System.out.println("Add User Seller Details i.e. SellerId");
            String sellerId = sc.nextLine();
            Seller user = new Seller();
            user.setUser(sellerId);
            sellers.put(sellerId, user);
        }
        catch(Exception ex){
            System.out.println("Error in creating seller");
        }
        finally{
            lock.writeLock().unlock();
        }
    }
}
