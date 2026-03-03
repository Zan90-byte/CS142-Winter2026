// Sahana
// Asteroid Purpose: Represents a small rock in space with random size, color, and destruction
// potential

package Space_Destruction.Space_Objects;

import java.awt.Color;
import java.util.Random;

public class Asteroid extends Planetoid{ // Inherits all fields from SpaceObject
    // Inherited fields: x, y, radius, color, voidSize
    // Inherited methods: getX(), getY(), gerR(), gerColor(), draw(Graphics g), detDistanceToStar()

    private static final Random rand = new Random();

    //constructor: x, y = actual map position (center + offset from star), orbitRadius = distance from star
    //orbitRadius = 5 - 10 and voidSize = 25 - 50
    public Asteroid(int x, int y, int orbitRadius){
        super(x, y,
                5 + rand.nextInt(6),  // Radius between 5 and 10 pixels
                25 + rand.nextInt(26), // Destruction wave effect 25 to 50 pixels
                orbitRadius); // Distance from star becomes orbitRadius parameter

        this.color = randomColor(); // Override default color with random color
    }

    // returns random color - grey/brown/dark grey
    private Color randomColor(){
        int choice = rand.nextInt(3);
        if(choice == 0){ return Color.GRAY;}
        if(choice == 1){ return new Color(101, 67, 37);}
        return Color.DARK_GRAY;
    }

    @Override
    public String toString(){ // Returns textual representation A (debugging)
        return "A";
    }
}
