// Jean and Adrianna
package Space_Destruction;
import Space_Destruction.Space_Objects.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.util.List;

public class GUIProgram extends JComponent {
    private int width;
    private int height;
    private DestructionMap objects;

    public GUIProgram (DestructionMap objects){
        this.objects = objects;
        JFrame frame = new JFrame();
        width = 550;
        height = 550;
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);

    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Drawing Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,width,height);
        // Drawing Space Objects
        List <SpaceObjects> sO = objects.getObjects();
        List <SpaceVoid> sV = objects.getVoids();

        for (int i=0;i<sO.size();i++){
            g2.setColor(sO.get(i).getColor());
            int xpos = sO.get(i).getX();
            int ypos = sO.get(i).getY();
            int rad = sO.get(i).getR()*2;
            g2.fillOval(xpos,ypos,rad,rad);
        }
        for(int i=0;i<sV.size();i++){
            g2.setColor(Color.RED);
            int xpos = sV.get(i).getX();
            int ypos = sV.get(i).getY();
            int rad = sV.get(i).getR()*2;
            /*
            getting rid of planet:
            for (int i=0;i<sO.size();i++){
                int sOX = sO.get(i).getX();
                int sOY = sO.get(i).getY();
                int sOR = sO.get(i).getR()*2;
                if (xpos==sOX&&ypos==sOY){
                    g2.drawOval(sOX,sOY,sOR,sOR);
                }
            }
             */
            g2.drawOval(xpos,ypos,rad,rad);
        }
        /*
        public void update(DestructionMap temp){
            objects = temp;
            repaint();
        }
         */
    }
}
