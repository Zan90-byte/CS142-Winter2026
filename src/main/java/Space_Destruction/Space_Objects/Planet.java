// Planet Purpose: Represents Planet objects in simulation

package Space_Destruction.Space_Objects;

import java.awt.*; // Imports classes for Color used in GUI
import java.util.Random; // Random class used for position, radius, and color

public class Planet extends Planetoid { // Inherits all fields and methods from Planetoid
    // Inherited fields: x, y, radius, color, voidSize, distanceFromStar
    // Inherited methods: takeDamage(), getDistanceFromStar(), draw(Graphics g)

    private Random rand = new Random();

    // Places Planet on ircular orbit at "orbit" units from star
    public Planet(int orbit) { // Orbit determines distance from the central star

        //Costructor
        x = new Random().nextInt(orbit + 1);
        y = (int) Math.sqrt(((orbit * orbit) - (x * x)) );
        radius = 10 + rand.nextInt(6); // Planet radius between 10 and 15
        voidSize = 60 + rand.nextInt(21); // Size of wave when destroyed between 60 and 80

        //super(x, y, radius, voidSize, orbit);
        // Wouldn't it be cleaner to call Planetoid constructor? Already has a constructor than sets these fields?

        // Full Quadrant Coverage? Right now only in positive x/y quadrant
//        if (rand.nextBoolean()) x *= -1;
//        if (rand.nextBoolean()) y *= -1;

        color = randomColor(); // Assigns Planet a random color from set
    }

    //Chooses a random Planet color between Orange, Green, and Blue
    private Color randomColor(){
        int choice = rand.nextInt(3);
        if(choice == 0){ return new Color(200,100, 0);}
        if(choice == 1){ return new Color(0,100, 0);}
        return new Color(50,100, 250);
    }
}

