package Space_Destruction.Space_Objects;

import java.awt.*;
import java.util.Random;

public class Planet extends Planetoid {

    private Random rand = new Random();

    public Planet(int orbit) {

        x = new Random().nextInt(orbit+1);
        y = (int) Math.sqrt(((orbit*orbit) - (x*x)) );
        radius = 15+rand.nextInt(6);
        voidSize = 50+rand.nextInt(26);
        color = randomColor();
    }

    private Color randomColor(){
        int choice = rand.nextInt(3);
        if(choice == 0){ return Color.ORANGE;}
        if(choice == 1){ return Color.GREEN;}
        return Color.BLUE;
    }
}