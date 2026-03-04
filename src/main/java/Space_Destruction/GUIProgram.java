// Adrianna + Jean + Vicktoria
// GUIProgram Purpose: Graphical front-end for destruction simulation
// Constructs a JFrame, generates background stars, simulation map is started, timer updates
// Shockwaves grow and fade, objects may be destroyed, and everything is continuously animated
// DestructionMap handles simulation logic, GUI handles drawing + animation

package Space_Destruction;
import Space_Destruction.Space_Objects.*; // Imports all space object classes

import javax.swing.*; // Imports JPanel, JFrame, Timer
import javax.swing.Timer;
import java.awt.*; // Imports graphics classes: Graphics, Graphics2D and Color
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*; // Imports ArrayList and Random

public class GUIProgram extends JPanel { // Allows GUIProgram to draw on JFrame


    private DestructionMap map; // Holds simulation state (list of all space objects and wave)
    // map.update() to advance simulation (can we make a tick with this?)
    // map.draw(Graphics g) can render objects
    private ArrayList<backgroundStars> stars; // List of static stars (don't move or get destroyed)
    private Timer timer; // Variable to control timer globally
    private boolean paused = false; // Variable to track if sim is paused


    public GUIProgram(DestructionMap map) {

        this.map = map; // Saves DestructionMap reference for later use
        stars = new ArrayList<>(); // Initializes star list as empty arraylist

        // Create a JFrame to display the simulation
        JFrame frame = new JFrame("Space Destruction Simulation"); // Create and name frame
        frame.setLayout(new BorderLayout()); // Adds space around simulation to add buttons

        frame.add(this, BorderLayout.CENTER); // Simulation panel in center

        // Add buttons for simulation control
        JPanel buttonPanel = new JPanel(); // Panel to actually hold buttons

        JButton pauseButton = new JButton("Pause"); //  Create Pause button
        JButton resumeButton = new JButton("Resume"); // Create Resume button
        JButton tickButton = new JButton("Tick"); // Create Tick button to move one frame exactly

        buttonPanel.add(pauseButton); // Add buttons to button panel
        buttonPanel.add(resumeButton);
        buttonPanel.add(tickButton);

        frame.add(buttonPanel, BorderLayout.SOUTH); // Place button panel on bottom of border

        // Animation timer
        Timer timer = new Timer(50, e -> { // Triggers code at fixed interval (50 ms)
            map.update(); // Advances simulation: expands wave, check obj destruction, update voids
            repaint(); // Triggers paintComponent(Graphics g) to redraw everything
        });

        timer.start(); // Begins repeated triggering (ticking) automatically

        // Button Actions
        pauseButton.addActionListener(e -> { // Pauses
            timer.stop();
            paused = true;
        });

        resumeButton.addActionListener(e -> { // Play/Resume
            timer.start();
            paused = false;
        });

        tickButton.addActionListener(e -> { // Ticks over ONE frame
            if (paused) {
                map.update();
                repaint();
            }
        });


        frame.setSize(800, 800); // Sets frame dimensions
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes program if window closed
        frame.setLocationRelativeTo(null); // Centers window on screen
        frame.add(this); // Adds GUIProgram to JFrame
        frame.setVisible(true); // Makes window appear

        //Distribute stars on frame when resized
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                genStars(); //Generates stars when frame is resized
                repaint(); //Repaints
            }
        });

       genStars(); //Generates Stars when simulations starts
       map.start(); //Starts simulation
    }

        private void genStars() {

            stars.clear(); //Clears stars before adding new ones
            Random rand = new Random(); // Random object
            int width = getWidth(); // Width of frame
            int height = getHeight(); // Height of frame

            if (width <= 0 || height <= 0) { // If you call genStars before frame height and width are valid
                return;                      // it won't break the code
            }

        // Randomly generates 1 star per 50x50 px for background
            int numStars = (getWidth() * getHeight()) / 2500; //Generates 1 star per 50x50 px
            for (int i = 0; i < numStars; i++) { //instead 200 stars used numStars to fill screen
                stars.add(new backgroundStars(
                    rand.nextInt(width), // Utilized width to randomly generate stars
                    rand.nextInt(height), // Utilized height to randomly generate stars
                    rand.nextInt(3) + 1 // Star size randomly between 1 and 3 pixels
            ));

        }

        map.start(); // Calls start on DestructionMap, initializes first wave at random planetoid

    }



    @Override
    protected void paintComponent(Graphics g) { // Called every repaint()
        super.paintComponent(g); // Clears previous frame

        setBackground(Color.BLACK); // Paints background black (gray by default)
        //g.fillRect(0, 0, getWidth(), getHeight()); // No need for this

        // Draws stars
        for (backgroundStars s : stars) { // Iterates through static star list
            s.draw(g); // Calls draw(Graphics g) on each star to make background
        }

        // Move origin (0, 0) to center
        Graphics2D g2 = (Graphics2D) g; // Graphics extension for line thickness, etc
        g2.translate(getWidth() / 2, getHeight() / 2); // Moves coordinate origin to panel center
        // Necessary because we place all objects relative to center star

        map.draw(g2); // Calls DestructionMap.draw which draws each object and each SpaceVoid
        // Effect is star centrally placed, planets/asteroids around it, shockwave moving outwards
    }


}