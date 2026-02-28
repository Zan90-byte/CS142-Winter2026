package Space_Destruction.Space_Objects;

import java.util.Random;

public abstract class Planet extends Planetoid {

    public static Random rand = new Random();

    public Planet(int x, int y, int radius, int voidSize, double distanceFromStar) {
        super(x, y, radius, voidSize, distanceFromStar);



    }
}
