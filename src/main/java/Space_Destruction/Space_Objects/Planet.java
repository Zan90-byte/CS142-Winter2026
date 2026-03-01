package Space_Destruction.Space_Objects;
import java.awt.*;
import java.util.Random;

public class Planet extends Planetoid {

    private Random rand = new Random();

    public Planet(int orbit) {

        //Costructor
        int x = new Random().nextInt(orbit+1);
        int y = (int) Math.sqrt(((orbit*orbit) - (x*x)) );
        int radius = 15 + rand.nextInt(6);
        int voidSize = 50 + rand.nextInt(26);

        //super(x, y, radius, voidSize, orbit);

        color = randomColor();

    }

    //Random color between Orange, Green, and Blue
    private Color randomColor(){
        int choice = rand.nextInt(3);
        if(choice == 0){ return Color.ORANGE;}
        if(choice == 1){ return Color.GREEN;}
        return Color.BLUE;
    }
}

