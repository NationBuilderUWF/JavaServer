package Map;
import Map.Tile.Tile;

public class Map{

    public Tile[][] tiles;
    public String _id;

    public Map(){
        this.tiles = new Tile[11][12];
        populateDarkZone();
    }

    public void populateDarkZone(){
        for(int i=2; i<=8; i++){
            for(int j=0; j<12; j++){
                if(i>=4 && i<=6){
                    getTile(i,j).setDarkFlag(true);
                }
                else if(j>=2 && j<=9){
                    getTile(i,j).setDarkFlag(true);
                }
            }
        }
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    public int[][] returnOwnership(){
        int[][] ownership = new int[11][12];
        for(int i=0; i<=11; i++){
            for(int j=0; i<12; j++){
                Nation owner = getTile(i,j).getOwner();
                if(owner == null)ownership[i][j] = 0;
                ownership[i][j] = owner.getID();
            }
        }
        return ownership;
    }

}
