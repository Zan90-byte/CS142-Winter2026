package Space_Destruction;

public class SpaceVoid {
    private int x;
    private int y;
    private int r;
    private int maxRad;


    public SpaceVoid(int x, int y, int maxRad){
        this.x = x;
        this.y = y;
        this.r = 0;
        this.maxRad = maxRad;
    }

    public void update(){
        if (r < maxRad){
            r++;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }
    public int getMax() { return maxRad; }

}

