//Star Purpose: Represents Star objects in simulation

package Space_Destruction.Space_Objects;
import java.awt.*; // Color class to make star yellow
import java.util.Random;

public class Star extends SpaceObjects { // Inherits all fields from SpaceObject
    // Inherited fields: x, y, radius, color, voidSize
    // Inherited methods: getX(), getY(), gerR(), gerColor(), draw(Graphics g), detDistanceToStar()

    // Default constructor (runs when creating a Star object)
    public Star(){
        x = 0; // Places Star at center of map (all orbits relative to this (0,0))
        y = 0;
        radius = 30; // Set radius to 30 pixels
        color = randomColor(); // Sets color to yellow
        voidSize = 100; // Destruction wave set
    }
    public String toString(){
        return "*";
    } // Returns textual * representation (debugging)

    private Color randomColor(){
        int choice = new Random().nextInt(4);
        if(choice == 0){ return Color.YELLOW;}
        if(choice == 1){ return new Color(255, 255, 175);}
        if(choice == 2){ return Color.WHITE;}
        return new Color(175,0, 0);
    }
}
