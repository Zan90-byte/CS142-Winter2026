package Space_Destruction.Space_Objects;
import java.awt.*;

public class Star extends SpaceObjects {
// Star extends Space object, spawns in the center of the map.
    public Star(){
        x = 0;
        y = 0;
        radius = 30;
        color = Color.YELLOW;
    }
    public String toString(){
        return "*";
    }


}
