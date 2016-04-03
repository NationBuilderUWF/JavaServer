package Map;

public class Nation {

    private int ID;
    private int resources;
    private boolean inBattle;

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

    public boolean isInBattle(){
        return inBattle;
    }

    public void setInBattle(boolean inBattle){
        this.inBattle = inBattle;
    }

    public Nation(int ID, int resources, boolean inBattle){
        this.ID = ID;
        this.resources = resources;
        this.inBattle = inBattle;
    }

}
