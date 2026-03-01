package Space_Destruction;

import java.util.*;

public class SpaceStorm {

    public static void main(String[] args) {

        // Create a map
        DestructionMap map = new DestructionMap(500, 3, 2);

        // Start simulation (create first void)
        map.start();

        // Run simulation steps
        for (int i = 0; i < 50; i++) {
            System.out.println("Step " + i);

            map.update();

            // Print objects
            for (var obj : map.getObjects()) {
                System.out.println(obj + " at (" + obj.getX() + ", " + obj.getY() + ")");
            }

            // Print voids
            for (var v : map.getVoids()) {
                System.out.println("Void at (" + v.getX() + ", " + v.getY() + ") r=" + v.getR());
            }

            System.out.println("------------------");
        }
    }
}
