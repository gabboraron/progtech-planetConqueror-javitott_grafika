package evfolyamzh;

import java.util.Objects;

/**
 *
 * @author Sándor
 */
public class Tile {
    private String type;
    private boolean occupied; 
    private int owner;
    private coordinate coord;
    private Game game;
    
    private int ptToOccupation;
    //private int currentPoints;
    private int nrOfAttacks;
    private int nrOfAttacksNeeded;
    
    public Tile(Game game, String type, coordinate coord) {
        this.game = game;
        occupied = false;
        nrOfAttacks = 0;
        this.coord = coord;
        switch(type){
            case "I" : this.type = "I";
                       occupied = true;
                       owner = 404;
                       break;
            case "C" : this.type = "C";
                       occupied = false;
                       nrOfAttacksNeeded = 5;
                       ptToOccupation = 100;
                       owner = 0;
                       break;
            case "P" : this.type = "P";
                       occupied = false;
                       nrOfAttacksNeeded = 2;
                       ptToOccupation = 15;
                       owner = 0;
                       break;
            case "M" : this.type = "M";
                       occupied = false;
                       nrOfAttacksNeeded = 3;
                       ptToOccupation = 40;
                       owner = 0;
                       break;
            case "F" : this.type = "F";
                       occupied = false;
                       nrOfAttacksNeeded = 3;
                       ptToOccupation = 50;
                       owner = 0;
                       break;
            case "V" : this.type = "V";
                       occupied = false;
                       nrOfAttacksNeeded = 4;
                       ptToOccupation = 75;
                       owner = 0;
                       break;
        }
    }

    /**
     * Get if this tile is occupied
     * @return 
     */
    public boolean isOccupied() {
        return occupied;
    }
    
    /**
     * Get the label what is on the tile in game panel
     * @return 
     */
    public String getLabelOfTile(){
        String s = "";
        if(!isOccupied()){
            if(type.equals("I")){
                s +="";
            } else {
                s += type + "(" + nrOfAttacks + "/" + nrOfAttacksNeeded + ")";
            }
            /*switch(type){
                case "I" : s += "";
                           break;
                case "C" : s += type + "(" + nrOfAttacks + "/" + nrOfAttacksNeeded + ")";
                           break;
                case "P" : s += type + "(" + nrOfAttacks + "/" + nrOfAttacksNeeded + ")";
                           break;
            }*/
        } else {
            if(type.equals("I")){
                s+="";
            } else {
                s += type;
            }
            /*switch(type){
                case "I" : s += "";
                           break;
                case "C" : s += type;
                           break;
                case "P" : s += type;
                           break;
            }*/
        }
        
        return s;
    }
    
    /**
     * Increment of the attack points in this tile. Also change the tile settings if it necessary;
     */
    public void incTileAttackPoints(){
        if(owner == 0){
            ++nrOfAttacks;
            
            if(nrOfAttacks == nrOfAttacksNeeded){
                occupied = true;
                switch(game.currentPlayer){
                    case 1 : owner = 1; break;
                    case 2 : owner = 2; break;
                }
            }
        }
    }
    
    /**
     * Decrement of the attack points in this tile. Also change the tile settings if it necessary;
     */
    public void decTileAttackPoints(){
        if(owner == 0){
            if(nrOfAttacks != 0)
                --nrOfAttacks;
            
            if(nrOfAttacks == nrOfAttacksNeeded){
                occupied = true;
                switch(game.currentPlayer){
                    case 1 : owner = 1; break;
                    case 2 : owner = 2; break;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format(type + " nrOfAttacksNeeded:" + nrOfAttacksNeeded + " coordinates: " + coord + "\n");
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + (this.occupied ? 1 : 0);
        hash = 79 * hash + this.owner;
        hash = 79 * hash + Objects.hashCode(this.coord);
        hash = 79 * hash + this.ptToOccupation;
        hash = 79 * hash + this.nrOfAttacks;
        hash = 79 * hash + this.nrOfAttacksNeeded;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (this.occupied != other.occupied) {
            return false;
        }
        if (this.owner != other.owner) {
            return false;
        }
        if (this.ptToOccupation != other.ptToOccupation) {
            return false;
        }
        if (this.nrOfAttacks != other.nrOfAttacks) {
            return false;
        }
        if (this.nrOfAttacksNeeded != other.nrOfAttacksNeeded) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        return true;
    }

    /**
     * Get Coordinates of tile
     * @return 
     */
    public coordinate getCoord() {
        return coord;
    }

    /**
     * Get the owner of this tile.
     * 0 - nobody
     * 1 - Player 1
     * 2 - Player 2
     * 404 - invalid Terrain
     * @return 
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Get how much points earns this terrain.
     * @return 
     */
    public int getPtToOccupation() {
        return ptToOccupation;
    }

    /**
     * Get the type sign
     * @return 
     */
    public String getType() {
        return type;
    }
    
}
