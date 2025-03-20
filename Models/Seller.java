package Models;

public class Seller extends User{
    private String userId;

    public String getUserId() {
        return userId;
    }

    @Override
    public void setUser(String userId){
        this.userId = userId;
    }
}
