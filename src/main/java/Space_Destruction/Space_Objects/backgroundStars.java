// backgroundStars Purpose: Represent a single star in the background to be used
// to generate a realistic backdrop to destruction simulation

package Space_Destruction.Space_Objects;

import java.awt.*;

public class backgroundStars { // encapsulated to prevent modifying
    private int x; // x-coordinate
    private int y; // y-coordinate
    private int size; // Diameter in pixels

    public backgroundStars(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    // Draws star in specific graphics context (Graphics g) provided by Swing paintComponent()
    // In other words, list created in GUI class
    public void draw(Graphics g) {
        g.setColor(Color.WHITE); // Sets color to white
        g.fillOval(x, y, size, size); // Creates circular pixel cluster
    }
}
