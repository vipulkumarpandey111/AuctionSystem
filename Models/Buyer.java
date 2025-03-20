package Models;

public class Buyer extends User{
    private String userId;

    @Override
    public void setUser(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
