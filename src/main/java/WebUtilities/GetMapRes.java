package WebUtilities;

import Map.Tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jason on 4/2/2016.
 */
public class GetMapRes implements Serializable {
    public static final long serialVersionUID = 1L;
    public ArrayList<Tile> maps;
    public ArrayList<Integer> banks;
}
