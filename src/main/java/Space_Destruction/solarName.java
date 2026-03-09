package Space_Destruction;
import java.util.Random;

public class solarName {
    public String [] sunName = {"Alpha", "Laika" , "Atlas" , "Ares" , "Polaris" , "Ursa", "Kepler"};
    public Random rand = new Random();
    public String name;

    public void genName(){
        int p1 = rand.nextInt(sunName.length);
        int p2 = rand.nextInt(4)+2;
        String cord = "";
        for (int i=1;i<= p2;i++){
            cord += ""+rand.nextInt(9);
        }
        name = sunName[p1]+" - "+cord;
    }
    public String toString(){
        return name;
    }


}
