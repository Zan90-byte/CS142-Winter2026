package Space_Destruction.Space_Objects;

import java.awt.*;

public class backgroundStars {
    private int x;
    private int y;
    private int size;

    public backgroundStars(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }
}
