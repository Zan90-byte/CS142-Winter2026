package Space_Destruction;
import Space_Destruction.Space_Objects.*;
//import Space_Destruction.Space_Objects.SpaceObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DestructionMap {
    private int scale;
    private List<SpaceObjects> objects;
    private List<SpaceVoid> voids;

    public DestructionMap(int scale, int numPlanets, int numAsteroid){
        this.scale = scale;
        objects = new ArrayList<SpaceObjects>();
        voids = new ArrayList<SpaceVoid>();
        fill(numPlanets, numAsteroid);
    }

    private void fill(int numPlanets, int numAsteroids){
        int p = 0;
        int a = 0;
        objects.add(new Star());
        while(a < numAsteroids || p < numPlanets){
            Planetoid temp;
            if (a >= numAsteroids){
//                temp = new Planet();
                p ++;
            } else if (p >= numPlanets){
//                temp = new Asteroid();
                a ++;
            } else {
                if (new Random().nextBoolean()){
//                    temp = new Planet();
                    p++;
                } else {
                    //add asteroids
                }
            }
//            objects.add(temp);
        }
    }

    public void update(){
        //update all voids in list of voids

        //check all objects for if within voids

    }

    public List<SpaceObjects> getObjects(){
        return objects;
    }

    public List<SpaceVoid> getVoids(){
        return voids;
    }

    public void start(){
        //adds initial void at one randomly selected planetoid
    }

}
