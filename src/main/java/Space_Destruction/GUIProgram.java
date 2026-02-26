// Jean and Adrianna
package Space_Destruction;
import Space_Destruction.Space_Objects.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.util.List;

public class GUIProgram {
    private int width;
    private int height;
    private List<DestructionMap> objects;

    public GUIProgram (List<DestructionMap> objects){
        this.objects = objects;
        JFrame frame = new JFrame();
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(this);
        frame.setVisible(true);

    }
    protected void paintComponent(Graphics g){

    }
}
