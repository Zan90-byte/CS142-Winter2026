package Space_Destruction.Space_Objects;

import java.awt.Color;
import java.util.Random;

public class Planetoid extends SpaceObjects {

    protected double distanceFromStar; // Distance from a central star
    private static final Random rand = new Random();

    // Constructor for dwarf planet-like planetoid placed along orbit
    // r: 10 - 15, voidSize: 40-70
    public Planetoid(int orbit) {
        x = rand.nextInt(orbit + 1);
        y = (int) Math.sqrt((orbit * orbit) - (x * x));
        if (rand.nextBoolean()) {x *= -1;}
        if (rand.nextBoolean()) {y *= -1;}
        radius = 10 + rand.nextInt(6);
        voidSize = 40 + rand.nextInt(31);
        distanceFromStar = orbit;
        color = randomColor();
    }


    // Constructor
    public Planetoid(int x, int y, int radius, int voidSize, double distanceFromStar) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.voidSize = voidSize;
        this.distanceFromStar = distanceFromStar;
    }

    public Planetoid(){};

    public double getDistanceFromStar() {
        return distanceFromStar;
    }

    // Destruction logic for planetoids (can be overridden in subclasses)
    public void takeDamage(int energy) {
        if (energy >= radius * 2) {

            // Change color to indicate destruction?
            color = Color.RED;
        }
    }

    // Same grey/brown palette as asteroids but alpha = 150 for reduced opacity
    private Color randomColor() {
        int choice = rand.nextInt(3);
        if (choice == 0) return new Color(128, 128, 128, 150);  // grey
        if (choice == 1) return new Color(101, 67, 37, 150);    // brown
        return new Color(64, 64, 64, 150);                    // dark grey
    }

    @Override
    public String toString() {
        return "D"; //"D" for dwarf
    }
}


