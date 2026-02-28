package Space_Destruction;
import Space_Destruction.Space_Objects.*;
//import Space_Destruction.Space_Objects.SpaceObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DestructionMap {
    private int scale;
    private List<SpaceObjects> objects;
    private List<SpaceVoid> voids;

    public DestructionMap(int scale, int numPlanets, int asteroidFields){
        this.scale = scale;
        objects = new ArrayList<SpaceObjects>();
        voids = new ArrayList<SpaceVoid>();
        fill(numPlanets, asteroidFields);
    }

    private void fill(int numPlanets, int asteroidFields){
        int p = 0;
        int a = 0;
        int orbit = 20;
        objects.add(new Star());
        while(a < asteroidFields || p < numPlanets){
            Planetoid temp;
            if (a >= asteroidFields){
                //if already exceeded num of asteroid fields
//                objects.add(new Planet(orbit));;
                p ++;
            } else if (p >= numPlanets){
                //if already exceeded num of planets
                int fieldSize = new Random().nextInt(4) + 1;
                makeAstField(orbit, fieldSize);
                a ++;
            } else if (new Random().nextBoolean()) {
                //randomly choose between making a planet or asteroid field
//                objects.add(new Planet(orbit));;
                p ++;
            } else {
                //add asteroids
                int fieldSize = new Random().nextInt(4) + 1;
                makeAstField(orbit, fieldSize);
                a ++;
            }
            orbit += 20 + new Random().nextInt(6);
        }
    }

    private void makeAstField(int orbit, int total){
        int numAsteroids = new Random().nextInt(3) + 1;
        List<Asteroid> field = new ArrayList<Asteroid>(numAsteroids);
        for (int i = 0; i < numAsteroids; i++){
            Asteroid temp = makeAsteroid(orbit);
            while (checkField(field, temp)){
                temp = makeAsteroid(orbit);
            }
        }
    }

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

    public void update(){
        //update all voids in list of voids
        for (int i = 0; i < voids.size(); i++){
            voids.get(i).update();
        }
        //check all objects for if within voids
        for (int v = 0; v < voids.size(); v++){
            for (int o = 0; 0 < objects.size(); o++){
                int x = voids.get(v).getX();
                int y = voids.get(v).getY();
                int dist = objects.get(o).getDistanceTo(x, y);
                dist -= objects.get(o).getR();
                if (dist <= voids.get(v).getR()){
                    //remove object and create new void at location
                    voids.add(new SpaceVoid(x, y, objects.get(o).getVoidSize()));
                    objects.remove(o);
                }
            }
        }
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

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

}
