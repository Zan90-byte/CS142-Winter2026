// Sahana

package Space_Destruction;

public class SpaceVoid {
    private int x;
    private int y;
    private int r;
    private int maxRad; // If voidSize is small (25 - 50), wave won't grow very far


    public SpaceVoid(int x, int y, int maxRad){
        this.x = x;
        this.y = y;
        this.r = 1;
        this.maxRad = maxRad;
    }

    public void update(){
        if (r < maxRad * 4){
            r+= 4; // If we want a slightly faster wave growth
            // If we remove the max, the destruction wave will spread until off-screen which could be cool?
            // r += 4; with no if
        }
//        r += 4;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }
    public int getMax() { return maxRad; }

}

