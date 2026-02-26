package Space_Destruction.Space_Objects;

import java.awt.Color;

public class Planetoid extends SpaceObjects {

    protected double distanceFromStar; // Distance from a central star

    // Constructor
    public Planetoid(int x, int y, int radius, int voidSize, double distanceFromStar) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.voidSize = voidSize;
        this.distanceFromStar = distanceFromStar;
    }

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
