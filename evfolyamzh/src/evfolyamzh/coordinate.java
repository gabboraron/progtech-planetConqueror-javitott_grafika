package evfolyamzh;



/**
 *
 * @author Sándor
 */
public class coordinate {
    public int x;
    public int y;

    public coordinate(){
    }

    public coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return String.format(x + " " + y);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
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
        final coordinate other = (coordinate) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
}