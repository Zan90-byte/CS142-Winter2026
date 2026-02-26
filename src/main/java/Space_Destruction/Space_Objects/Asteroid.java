package Space_Destruction.Space_Objects;
import java.awt.*;
public abstract class SpaceObjects {
    private int x;
    private int y;
    private int radius;
    private Color color;
    private int voidSize;


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
    public int getDistanceTo(int x2, int y2){
        return (int) Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
    }
    public int getVoidSize(){
        return voidSize;
    }

