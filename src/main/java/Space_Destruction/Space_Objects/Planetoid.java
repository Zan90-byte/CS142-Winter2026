// Planetoid Purpose: Represents planets or small planetary objects in simulation

package Space_Destruction.Space_Objects;

import java.awt.Color; // Color object used by GUI
import java.util.Random; // Access Random class to generate random positions

// Inherits all fields and methods from abstract SpaceObjects class [including draw(Graphics g)]
public class Planetoid extends SpaceObjects {

    protected double distanceFromStar; // Distance from the central star (placement + interactions)
    private static final Random rand = new Random(); // Shared for positions, colors, sizes

    // Constructor for dwarf planet-like planetoid placed along orbit
    // r: 10 - 15, voidSize: 40-70
    public Planetoid(int orbit) { // Creates planetoid along orbit a given distance from star
        x = rand.nextInt(orbit + 1); // Picks x randomly along [0, orbit]
        y = (int) Math.sqrt((orbit * orbit) - (x * x)); // Circle eqt to calculate y
        if (rand.nextBoolean()) {x *= -1;} // Random quadrant assignment
        if (rand.nextBoolean()) {y *= -1;} // Flips signs randomly
        radius = 7 + rand.nextInt(4); // Assigns size between 7 and 10 for Planetoid
        voidSize = 40 + rand.nextInt(31); // Sets destruction wave to 40-70
        distanceFromStar = orbit; // Stores distance from star
        color = randomColor(); // Chooses a semi-transparent grey/brown/dark grey color
    }

    // Constructor to create fully specified Planetoid
    public Planetoid(int x, int y, int radius, int voidSize, double distanceFromStar) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.voidSize = voidSize;
        this.distanceFromStar = distanceFromStar;
    }

    // Default constructor
    public Planetoid(){};

    public double getDistanceFromStar() { // Returns distance from central star
        return distanceFromStar;
    }

    // Destruction logic for planetoids (visually marks Planetoid as destroyed)
    public void takeDamage(int energy) {
        if (energy >= radius * 2) { // If energy applied exceeds 2x radius, object is destroyed

            // Change color to indicate destruction?
            color = Color.RED; // Changes red
        }
    }

    // Returns same grey/brown palette as asteroids but alpha = 150 for reduced opacity
    private Color randomColor() {
        int choice = rand.nextInt(3);
        if (choice == 0) return new Color(228, 228, 228, 150);  // grey
        if (choice == 1) return new Color(151, 117, 77, 150);    // brown
        return new Color(164, 164, 164, 150);                    // dark grey
    }

    @Override
    public String toString() { // Returns D to textually represent dwarf-like Planetoid
        return "D"; //"D" for dwarf
    }
}


