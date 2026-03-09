package Space_Destruction;
import java.util.Random;

public class solarName {


    public static String genName(){
        String [] sunName = {"Alpha", "Laika" , "Atlas" , "Ares" , "Polaris" , "Ursa", "Kepler"};
        Random rand = new Random();
        String name;
        int p1 = rand.nextInt(sunName.length);
        int p2 = rand.nextInt(4)+2;
        String cord = "";
        for (int i=1;i<= p2;i++){
            cord += ""+rand.nextInt(9);
        }
        name = sunName[p1]+" - "+cord;
        return name;
    }
//    public String toString(){
//        return name;
//    }


}
