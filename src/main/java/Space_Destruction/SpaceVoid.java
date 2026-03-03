// Sahana
// SpaceVoid Purpose: simulate a destruction wave that spreads through space
// Particularly controls growing, fading, and extra visual effects

package Space_Destruction;

import java.awt.*;  // Accesses classes: Graphics, Graphics2D, Color, BasicStroke, RenderingHints

public class SpaceVoid {
    private int x; // x coordinate of shockwave center (destroyed object generating wave)
    private int y; // y coordinate of shockwave center (destroyed object generating wave)
    private int r; // Current radius of wave (starts at 1 and expands over time)
    private int maxRad; // If voidSize is small (25 - 50), wave won't grow very far
    private int alpha = 255; // Transparency of wave (255 = fully visible, 0 = invisible)
    private int life = 0; // Counts # of frames wave has existed for timing effects

    public SpaceVoid(int x, int y, int maxRad){
        this.x = x; // Initializes new wave at (x, y) with target radius maxRad
        this.y = y;
        this.r = 1; // Sets initial radius at 1
        this.maxRad = maxRad;
    }

    // Advances the shockwave each frame
    public void update(){
        life++; // Counts frames (as "age" of wave) for visual effects

        if (r < maxRad * 4){ // Expands radius quickly for explosive effect
            r += 6;  // Wave grows 6 pixels per frame
        }

        if (life > 20 && alpha > 0){ // Delays fading of wave for 20 frames
            alpha -= 3;  // Decreases alpha (visibility) to simulate fading
        }
    }

    // Getters for position, radius, and max radius
    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }
    public int getMax() { return maxRad; }

    public boolean isFinished(){ // Returns true when wave is invisible
        return alpha <= 0; // DestructionMap will remove finished waves from list
    }

    // Responsible for actual rendering of destruction wave rings
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g; // Enhanced graphics (line thickness, transparency, etc)

        // INNER FLASH CORE: Initial flash bright and small in center
        int coreAlpha = Math.min(255, alpha + 50); // Small bright center blast
        g2.setColor(new Color(255, 255, 255, coreAlpha));
        g2.fillOval(x - r/4, y - r/4, r/2, r/2); // Offset to center smaller circle

        // PLASMA HALO: Creates a fake glowing effect behind main ring
        g2.setColor(new Color(255, 255, 255, alpha / 2)); // Semi-transparent white (2 vs 3 rn)
        g2.fillOval(x - r, y - r, r * 2, r * 2); // Fills main circle

        // GLOWING OUTER RING: Creates a bold wavefront
        g2.setStroke(new BasicStroke(3)); // Sets line thickness
        g2.setColor(new Color(255, 255, 255, alpha)); // Links transparency to alpha for fade out
        g2.drawOval(x - r, y - r, r * 2, r * 2); // Draws outer ring

//        // SECOND OUTER RING: Creates a sci-fi like energy ripple around wave
//        g2.setStroke(new BasicStroke(2)); // Sets line thickness
//        g2.setColor(new Color(255, 255, 255, alpha / 2)); // Halved alpha for less opacity
//        g2.drawOval(x - r - 10, y - r - 10, (r + 10) * 2, (r + 10) * 2); // Motion
        // Effect feels a little overkill?
    }
}

