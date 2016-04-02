package Map;

public class Nation {

    private int ID;
    private int resources;

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public Nation(int ID, int resources){
        this.ID = -1;
        this.resources = 0;
    }

}
