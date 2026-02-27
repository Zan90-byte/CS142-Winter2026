package Space_Destruction;

import Space_Destruction.Space_Objects.SpaceObjects;

import java.util.ArrayList;
import java.util.List;

public class DestructionMap {
    private int scale;
    private List<SpaceObjects> objects;
    private List<SpaceVoid> voids;

    public DestructionMap(int scale){
        this.scale = scale;
        objects = new ArrayList<SpaceObjects>();
        voids = new ArrayList<SpaceVoid>();
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
