// Jean and Adrianna
package Space_Destruction;
import Space_Destruction.Space_Objects.*;
import Space_Destruction.Space_Objects.SpaceObjects;
import Space_Destruction.SpaceVoid;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Graphics;
import java.util.List;


public class GUIProgram extends JPanel{
    private int width;
    private int height;
    private Space_Destruction.DestructionMap map; // need a map, not list


    public GUIProgram(Space_Destruction.DestructionMap map) {
        this.map = map;

        JFrame frame = new JFrame("Space Destruction Simulation");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);


        map.start(); // Start first void


        // Animation timer (20 FPS)
        Timer timer = new Timer(50, e -> {
            map.update();
            repaint();
        });

        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        // Paint background black manually (grey by default)
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());


        // Move origin (0,0) to center
        g.translate(getWidth() / 2, getHeight() / 2);


        // OPTIONAL: What if we add tiny stars in the back? More space-like?
        // updates crazy because random. Maybe a stable version?
//        g.setColor(Color.WHITE);
//        for (int i = 0; i < 200; i++) {
//            int x = (int)(Math.random() * getWidth()) - getWidth()/2;
//            int y = (int)(Math.random() * getHeight()) - getHeight()/2;
//            g.fillRect(x, y, 1, 1);
//        }


        // Draw space objects
        for (SpaceObjects obj : map.getObjects()) {
            g.setColor(obj.getColor());
            g.fillOval(
                    obj.getX() - obj.getR(),
                    obj.getY() - obj.getR(),
                    obj.getR() * 2,
                    obj.getR() * 2
            );
        }


        // Draw voids (black expanding circles)
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3)); // make circle thicker
        g2.setColor(Color.WHITE);


//        for (SpaceVoid v : map.getVoids()) {
//            g.drawOval(
//                    v.getX() - v.getR(),
//                    v.getY() - v.getR(),
//                    v.getR() * 2,
//                    v.getR() * 2
//            );
//        }


        // What if we make one outer ring bright and the inner circle semi-transparent like a real shockwave?
        for (SpaceVoid v : map.getVoids()) {


            // semi-transparent white
            g2.setColor(new Color(255, 255, 255, 80));


            g2.fillOval(
                    v.getX() - v.getR(),
                    v.getY() - v.getR(),
                    v.getR() * 2,
                    v.getR() * 2
            );


            // outer bright ring
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(
                    v.getX() - v.getR(),
                    v.getY() - v.getR(),
                    v.getR() * 2,
                    v.getR() * 2
            );
        }
    }


}