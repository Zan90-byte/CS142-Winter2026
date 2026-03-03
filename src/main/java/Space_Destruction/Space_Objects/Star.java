//Star Purpose: Represents Star objects in simulation

package Space_Destruction.Space_Objects;
import java.awt.*; // Color class to make star yellow

public class Star extends SpaceObjects { // Inherits all fields from SpaceObject
    // Inherited fields: x, y, radius, color, voidSize
    // Inherited methods: getX(), getY(), gerR(), gerColor(), draw(Graphics g), detDistanceToStar()

    // Default constructor (runs when creating a Star object)
    public Star(){
        x = 0; // Places Star at center of map (all orbits relative to this (0,0))
        y = 0;
        radius = 30; // Set radius to 30 pixels
        color = Color.YELLOW; // Sets color to yellow
        voidSize = 100; // Destruction wave set
    }
    public String toString(){
        return "*";
    } // Returns textual * representation (debugging)

}
