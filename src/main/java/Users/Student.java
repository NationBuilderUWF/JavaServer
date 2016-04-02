package Users;
import Map.Nation;
public class Student {
    private Nation myNation;
    private String user;

    public Nation getMyNation() {
        return myNation;
    }

    public void setMyNation(Nation myNation) {
        this.myNation = myNation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Student(Nation myNation, String user){
        this.myNation = myNation;
        this.user = user;
    }
}
