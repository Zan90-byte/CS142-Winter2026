package Space_Destruction.Space_Objects;

import java.util.Random;

public class Planet extends Planetoid {

    private static Random rand = new Random();

    public Planet(int x, int y, int radius, int voidSize, double distanceFromStar) {
        super(x, y, radius, voidSize, distanceFromStar);


    }
}
