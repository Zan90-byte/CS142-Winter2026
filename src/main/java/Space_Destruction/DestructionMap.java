package Space_Destruction;
import Space_Destruction.Space_Objects.*;
//import Space_Destruction.Space_Objects.SpaceObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DestructionMap {
    private int scale;
    private List<SpaceObjects> objects;
    private List<SpaceVoid> voids;

    //constructor
    public DestructionMap(int scale, int numPlanets, int asteroidFields){
        this.scale = scale;
        objects = new ArrayList<SpaceObjects>();
        voids = new ArrayList<SpaceVoid>();
        fill(numPlanets, asteroidFields);
    }

    //fills lists
    private void fill(int numPlanets, int asteroidFields){
        int p = 0;
        int a = 0;
        //objects(0) = the star
        objects.add(new Star());
        int orbit = 40 + objects.get(0).getR();
        //fil rest of objects list with planets and asteroids fields
        while(a < asteroidFields || p < numPlanets){
            Planetoid temp;
            if (a >= asteroidFields){
                //if already exceeded num of asteroid fields just add planets
                objects.add(new Planet(orbit));
                p ++;
            } else if (p >= numPlanets){
                //if already exceeded num of planets just add asteroid fields
                int fieldSize = new Random().nextInt(4) + 1;
                makeAstField(orbit, fieldSize);
                a ++;
            } else if (new Random().nextBoolean()) {
                //randomly choose between making a planet or asteroid field
                //add planet
                objects.add(new Planet(orbit));
                p ++;
            } else {
                //add asteroids
                int fieldSize = new Random().nextInt(4) + 1;
                makeAstField(orbit, fieldSize);
                a ++;
            }
            //increment orbit by 20-40 + radius of last object
            orbit += objects.get(objects.size()-1).getR() + 20 + new Random().nextInt(21);
            //orbit += 20 + new Random().nextInt(6);
//            orbit += 60 + new Random().nextInt(20); // overlapping objects in sim
        }
    }

    //makes a field of asteroids around a specific orbit (radius around sun)
    private void makeAstField(int orbit, int total){
        int numAsteroids = new Random().nextInt(3) + 1;
        List<Asteroid> field = new ArrayList<Asteroid>(numAsteroids);

        for (int i = 0; i < numAsteroids; i++){
            Asteroid temp = makeAsteroid(orbit);

            while (checkField(field, temp)){
                temp = makeAsteroid(orbit);
            }

            field.add(temp);  // Asteroids created but not stored so fixed
            objects.add(temp);
        }
    }

    //gives asteroid to simplify makeAstField()
    private Asteroid makeAsteroid(int orbit){
        int x = new Random().nextInt(orbit+1);
        int y = (int) Math.sqrt(((orbit*orbit) - (x*x)) );
        //randomizes the quadrant
        if (new Random().nextBoolean()){
            x *= -1;
        }
        if (new Random().nextBoolean()){
            y *= -1;
        }
        return new Asteroid(x, y, orbit);
    }

    //use to make sure no overlapping asteroids
    private boolean checkField(List<Asteroid> field, Asteroid temp){
        for (int i = 0; i < field.size(); i++){
            Asteroid asteroid = field.get(i);
            if (asteroid.getDistanceTo(temp.getX(), temp.getY()) <= (temp.getR())+asteroid.getR() + 5){
                return true;
            }
        }
        return false;
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

    //update voids than use that to destroy space objects
    public void update(){

        // Update and remove finished voids
        for (int v = 0; v < voids.size(); v++) {
            SpaceVoid currentVoid = voids.get(v);
            currentVoid.update(scale);

            if (currentVoid.isFinished()) {
                voids.remove(v);
                v--;
            }
        }

        // Check collision between voids and objects
        for (int v = 0; v < voids.size(); v++) {

            int voidX = voids.get(v).getX();
            int voidY = voids.get(v).getY();
            int voidR = voids.get(v).getR();

            for (int o = objects.size() - 1; o >= 0; o--) {

                SpaceObjects obj = objects.get(o);

                int dist = obj.getDistanceTo(voidX, voidY);

                if (dist <= voidR) {

                    // Create new void at destroyed object location
                    voids.add(new SpaceVoid(
                            obj.getX(),
                            obj.getY(),
                            obj.getVoidSize()
                    ));

                    objects.remove(o);
                }
            }
        }
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

    //get lists of space objects and list of spaceVoids

    public List<SpaceObjects> getObjects(){
        return objects;
    }

    public List<SpaceVoid> getVoids(){
        return voids;
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

    public void start(){
        //adds initial void at one randomly selected Planetoid, destroying that Planetoid in precess
        int choice = 1 + new Random().nextInt(objects.size() - 1);
        int x = objects.get(choice).getX();
        int y = objects.get(choice).getY();
        int maxRad = objects.get(choice).getVoidSize();
        voids.add(new SpaceVoid(x, y, maxRad));
        objects.remove(choice);
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

    public void setScale(int scale){
        this.scale = scale;
    }

    public int getScale(){
        return scale;
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----


    public void draw(Graphics g) {

        for (SpaceObjects obj : objects) {
            obj.draw(g);
        }

        for (SpaceVoid v : voids) {
            v.draw(g);
        }
    }

}
