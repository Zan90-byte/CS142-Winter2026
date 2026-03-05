// SpaceStormMain Purpose: Main class for launching the simulation

package Space_Destruction;

public class SpaceStormMain {

    public static void main(String[] args) {
        // Creates new DestructionMap object and assigns it to variable map
        // DestructionMap initializes list of objects and voids, adds central star, randomly
        // generates planets and asteroid fields
        Space_Destruction.DestructionMap map = new Space_Destruction.DestructionMap(5, 4, 3);

        new Space_Destruction.GUIProgram(map); // Creates GUI and passes it map
        // Stores map as field, creates background stars, creates JFrame, starts simulation
        // timer to call map.update() and repaint() to expand destruction waves and destroy
        // objects in their path then redraw the panel of undestroyed objects
    }
}

// VERSION 1 Printing to Console
//    public static void main(String[] args) {
//
//        // Create a map
//        DestructionMap map = new DestructionMap(500, 3, 2);
//
//        // Start simulation (create first void)
//        map.start();
//
//        // Run simulation steps
//        for (int i = 0; i < 50; i++) {
//            System.out.println("Step " + i);
//
//            map.update();
//
//            // Print objects
//            for (Space_Destruction.Space_Objects.SpaceObjects obj : map.getObjects()) {
//                System.out.println(obj + " at (" + obj.getX() + ", " + obj.getY() + ")");
//            }
//
//            // Print voids
//            for (SpaceVoid v : map.getVoids()) {
//                System.out.println("Void at (" + v.getX() + ", " + v.getY() + ") r=" + v.getR());
//            }
//
//            System.out.println("------------------");
//        }
//    }
//
