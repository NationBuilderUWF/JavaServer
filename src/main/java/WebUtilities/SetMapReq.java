package WebUtilities;

import Map.Tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Jason on 4/2/2016.
 */
public class SetMapReq implements Serializable {
    public ArrayList<Tile> maps;
    public static final long serialVersionUID = 1L;
    public ArrayList<Integer> banks;
}
