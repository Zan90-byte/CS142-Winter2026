package Space_Destruction.Space_Objects;

import java.awt.Color;
import java.util.Random;

public class Asteroid extends Planetoid{

    private static final Random rand = new Random();

    //constructor: x, y = actual map position (center + offset from star), orbitRadius = distance from star
    //orbitRadius = 5 - 10 and voidSize = 25 - 50
    public Asteroid(int x, int y, int orbitRadius){
        super(x, y, 5 + rand.nextInt(6), 25 + rand.nextInt(26), orbitRadius);
        this.color = randomColor();
    }

    // returns random color - grey/brown/dark grey
    private Color randomColor(){
        int choice = rand.nextInt(3);
        if(choice == 0){ return Color.GRAY;}
        if(choice == 1){ return new Color(101, 67, 37);}
        return Color.DARK_GRAY;
    }

    @Override
    public String toString(){
        return "A";
    }
}
