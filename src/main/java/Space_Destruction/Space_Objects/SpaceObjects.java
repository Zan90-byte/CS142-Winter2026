// SpaceObject Purpose: Serves as base class for all objects in simulation by providing common
// properties and methods for all other space objects (position, size, color, draw() method)

package Space_Destruction.Space_Objects;

import java.awt.*;

// Abstract cannot create SpaceObjects directly (only subclasses can be instantiated)
public abstract class SpaceObjects {
    protected int x; // horizontal coordinate of object visible only to subclasses
    protected int y; // With x, determines object position on 2D simulation plane
    protected int radius; // Size of object used in drawing and collision calcs
    protected Color color; // Color of object
    protected int voidSize; // Size of explosion when destroyed (determines wave size)

    // Getter methods to retrieve properties to draw objects, calculate distances, if destroy
    public Color getColor(){
        return color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getR(){
        return radius;
    }
    public int getDistanceTo(int x2, int y2){ // Distance calculation
        return (int) Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
        // Checks if SpaceVoid reaches object (collisions or overlaps)
    }
    public int getVoidSize(){
        return voidSize;
    }
    public String toString(){return "?";} // Returns ? for unknown objects (debugging)

    // Draw object using Graphics
    public void draw(Graphics g) {
        g.setColor(getColor()); // Sets drawing color to current object color (based on class spec)
        //g.fillOval(x, y, radius, radius); // Draws filled circle of radius size
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2); // Centers
        // Subclasses can draw the same way or override
    }

}
