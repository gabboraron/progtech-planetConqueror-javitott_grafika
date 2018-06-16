package evfolyamzh;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Sándor
 */
class Game {
    private Vector tiles = new Vector();
    
    public int currentPlayer;
    public Player player1;
    public Player player2;

    public Game(int Rsize, int Csize) {
        this.currentPlayer = 1;
        this.player1 = new Player();
        this.player2 = new Player();
        
        initGamePanel(Rsize, Csize);
        System.out.println("Terrain: \n" + tiles);
    }

    private void initGamePanel(int Rsize, int Csize) {
        System.out.println("GAME PANEL SIZE: Row: " + Rsize + " Col: " + Csize);
        for(int ridx=0; ridx<Rsize; ++ridx){
            coordinate emtyCoord = new coordinate(ridx, randInt(0,Csize-1));
            boolean emptyDone = false;
            System.out.println("EMPTY TILE IN ROW " + ridx + ": " + emtyCoord);
            for(int cidx=0; cidx<Csize; ++cidx){
                coordinate currentCoord = new coordinate(ridx, cidx);
                if(currentCoord.equals(emtyCoord)){                     //empty tile case
                    Tile tile = new Tile(this, "I",emtyCoord);
                    tiles.add(tile);
                    emptyDone = true;
                } else {                                                //randomized tile case
                    Tile tile = new Tile(this, genereteTypeOfTile(),currentCoord);
                    tiles.add(tile);                    
                }
            }
        }
    }
    
    /**
     * Random number generator between min-max range.
     * @param min
     * @param max
     * @return 
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     * Generates type of tile
     * @return 
     */
    private String genereteTypeOfTile() {
        int random = randInt(0,4);
        String s = "";
        switch(random){
            case 0 : s = "C";
                     break;
            case 1 : s = "P";
                     break;
            case 2 : s = "M";
                     break;
            case 3 : s = "F";
                     break;
            case 4 : s = "V";
                     break;
                    
        }
 /*       if(random == 0){
            s = "C";
        } else {
            s = "P";
        }*/
        return s;
    }
    
    /**
     * Search by coordinates in the vector of terrain.
     * @return a Tile from terrain vector in the specific coordinates
     */
    public Tile getTileBycoordinates(coordinate coord){
        Tile t = new Tile(this, "I", coord);
        int idx = 0;
        boolean found = false;
        while((idx<tiles.size()) && (found == false)){
            Tile tmp = (Tile) tiles.get(idx);
            if(tmp.getCoord().equals(coord)){
                t = tmp;
                found = true;
                return tmp;
            }
            ++idx;
        }
        return t;
    }

    /**
     * Change the current player
     */
    void changePlayer() {
        if (currentPlayer == 1){
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }

    /**
     * Count players points and occupied territories
     */
    void countPlayerspoints() {
        player1.points = 0;
        player2.points = 0;
        
        player1.occupiedTiles = 0;
        player2.occupiedTiles = 0;
        
        for(int idx = 0; idx<tiles.size(); ++idx){
            if(((Tile) tiles.get(idx)).isOccupied()){
                if(((Tile) tiles.get(idx)).getOwner() == 1){
                    player1.points += ((Tile) tiles.get(idx)).getPtToOccupation();
                    player1.occupiedTiles = player1.occupiedTiles + 1;
                } else {
                    player2.points += ((Tile) tiles.get(idx)).getPtToOccupation();
                    player2.occupiedTiles = player2.occupiedTiles + 1;
                }
            }
        }
    }
    
    /**
     * Get if all tiles is occupied by a player. Return true if yes, false if is it there more available tiles
     * @return 
     */
    public boolean isWinner(){
        boolean isWin = true;
        System.out.println("isWin: " + isWin);
        for(int idx = 0; idx<tiles.size(); ++idx){
            if(!((Tile) tiles.get(idx)).isOccupied())
                isWin = false;
        }
        System.out.println("isWin: " + isWin);        
        return isWin;
    }
}
