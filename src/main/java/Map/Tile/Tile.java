package Map.Tile;
import Map.Map;
import Map.Nation;
import MapRender.SelectData;

public class Tile {

    private Map map;
    private Nation owner; //nation which owns tile
    private boolean darkFlag; //flag for dark zone tiles, which allow pvp
    private boolean defendFlag; //flag for defending against attack
    private Nation attacker; //nation attacking tile

    public Nation getOwner() {
        return owner;
    }

    public void setOwner(Nation owner) {
        this.owner = owner;
    }

    public boolean isDarkFlag() {
        return darkFlag;
    }

    public void setDarkFlag(boolean darkFlag) {
        this.darkFlag = darkFlag;
    }

    public boolean isDefendFlag() {
        return defendFlag;
    }

    public void setDefendFlag(boolean defendFlag) {
        this.defendFlag = defendFlag;
    }

    public Nation getAttacker() {
        return attacker;
    }

    public void setAttacker(Nation attacker) {
        this.attacker = attacker;
    }

    public void declareBattle(Nation attacker, int cost){
        Tile defender = map.getTile((int)SelectData.row, (int)SelectData.col);
        int adjacent = checkAdjacency(attacker);
        if(adjacent != 1){
            if(adjacent == 2){
                //displayError("tileAlreadyOwned");
                return;
            }
            if(adjacent == 0){
                //displayError("noAdjacentTiles");
                return;
            }
        }
        if(!defender.isDarkFlag()){ //if tile isn't a dark zone tile
            //displayError("notDarkZoneTile"); //placeholder for actual error displaying
            return;
        }
        if(defender.isDefendFlag()){ //if time is already in a battle
            //displayError("tileAlreadyInBattle");
            return;
        }
        if(attacker.getResources() > cost){ //if nation can afford battle
            attacker.setResources(attacker.getResources() - cost); //remove resources for cost
            defender.setAttacker(attacker);
            defender.setDefendFlag(true);
            //database.pushNationQuestions(attacker, 1); //for each student in nation push 1 question
            //database.pushNationQuestions(defender.getOwner());
        }
        else{
            //displayError("notEnoughResources");
            return;
        }
    }

    public void resolveBattle(Tile defender, boolean defenderWin){ //server might handle this?
        if(!defenderWin){
            defender.setOwner(defender.getAttacker());
        }
        defender.setAttacker(null);
        defender.setDefendFlag(false);
    }

    public void buyTile(Nation buyer, int cost){
        Tile product = map.getTile((int)SelectData.row,(int)SelectData.col); //retrieve desired tile
        int adjacent = checkAdjacency(buyer);
        if(adjacent != 1){
            if(adjacent == 2){
                //displayError("tileAlreadyOwned");
                return;
            }
            if(adjacent == 0){
                //displayError("noAdjacentTiles");
                return;
            }
        }
        if(product.getOwner() != null){
            //displayError("tileAlreadyOwned");
            return;
        }

        if(buyer.getResources() > cost){ //if nation can afford tile
            buyer.setResources(buyer.getResources() - cost); //remove resources for cost
            product.setOwner(buyer); //buyer now owns tile
        }
        else{
            //displayError("notEnoughResources");
            return;
        }
    }

    public int checkAdjacency(Nation myNation){
        if(map.getTile((int)SelectData.row, (int)SelectData.col).getOwner() == myNation)return 2;
        //^returns if tile of interest is already owned
        try {
            if(map.getTile((int)SelectData.row+1, (int)SelectData.col).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile below does not exist.");
        }
        try {
            if(map.getTile((int)SelectData.row-1, (int)SelectData.col).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile above does not exist.");
        }
        try {
            if(map.getTile((int)SelectData.row, (int)SelectData.col+1).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile to right does not exist.");
        }
        try {
            if(map.getTile((int)SelectData.row, (int)SelectData.col-1).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile to left does not exist.");
        }
        try {
            if(map.getTile((int)SelectData.row+1, (int)SelectData.col+1).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile below and to right does not exist.");
        }
        try {
            if(map.getTile((int)SelectData.row+1, (int)SelectData.col-1).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile below and to left does not exist.");
        }
        try {
            if(map.getTile((int)SelectData.row-1, (int)SelectData.col+1).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile above and to right does not exist.");
        }
        try {
            if(map.getTile((int)SelectData.row-1, (int)SelectData.col-1).getOwner() == myNation)return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile above and to left does not exist.");
        }
        return 0;//no tiles adjacent
    }

    public Tile(Map map, Nation owner, boolean darkFlag, boolean defendFlag, Nation attacker){
        this.map = map;
        this.owner = null;
        this.darkFlag = false;
        this.defendFlag = false;
        this.attacker = null;
    }

}
