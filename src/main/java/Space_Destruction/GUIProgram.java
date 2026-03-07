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
    private boolean paused = true; // Variable to track if sim is paused
    private boolean started = false;


    public GUIProgram(DestructionMap map) {

        this.map = map; // Saves DestructionMap reference for later use
        stars = new ArrayList<>(); // Initializes star list as empty arraylist

        // Create a JFrame to display the simulation
        JFrame frame = new JFrame("Space Destruction Simulation"); // Create and name frame
        frame.setLayout(new BorderLayout()); // Adds space around simulation to add buttons

        frame.add(this, BorderLayout.CENTER); // Simulation panel in center

        // Add buttons for simulation control
        JPanel buttonPanel = new JPanel(); // Panel to actually hold buttons

        JButton startButton = new JButton("Start"); // Create a Start button
        JButton pauseButton = new JButton("Pause"); //  Create Pause button
//        JButton resumeButton = new JButton("Resume"); // Create Resume button //merged with pause
        //JButton stopButton = new JButton("Stop"); // Maybe add a stop button?
        JButton tickButton = new JButton("Tick"); // Create Tick button to move one frame exactly
        JButton restartButton = new JButton("Restart");

        buttonPanel.add(restartButton);
        buttonPanel.add(startButton); // Add buttons to button panel
        buttonPanel.add(pauseButton);
//        buttonPanel.add(resumeButton); //merged with pause
        buttonPanel.add(tickButton);

        frame.add(buttonPanel, BorderLayout.SOUTH); // Place button panel on bottom of border

        // Info panel on the right side - displays live counts of remaining space objects
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.BLACK);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // count lables and status
        JLabel planetLabel    = new JLabel("Planets: -");
        JLabel asteroidLabel  = new JLabel("Asteroids: -");
        JLabel planetoidLabel = new JLabel("Planetoids: -");
        JLabel simStatusLabel = new JLabel(" ");
//        JLabel solarSystemName = new JLabel("NAME HERE");   // uncomment these when name generator is complete

        simStatusLabel.setForeground(Color.RED);
        planetLabel.setForeground(Color.WHITE);
        asteroidLabel.setForeground(Color.WHITE);
        planetoidLabel.setForeground(Color.WHITE);
//        solarSystemName.setForeground(Color.WHITE);   // ----- ----- -----

        planetLabel.setAlignmentX(CENTER_ALIGNMENT);
        asteroidLabel.setAlignmentX(CENTER_ALIGNMENT);
        planetoidLabel.setAlignmentX(CENTER_ALIGNMENT);
        simStatusLabel.setAlignmentX(CENTER_ALIGNMENT);
//        solarSystemName.setAlignmentX(CENTER_ALIGNMENT);  // ----- ----- -----

        infoPanel.add(planetLabel);
        infoPanel.add(asteroidLabel);
        infoPanel.add(planetoidLabel);
        infoPanel.add(simStatusLabel);
//        infoPanel.add(solarSystemName); // ----- ----- -----

        // End button - only visible when chain reaction is complete
        JButton endButton = new JButton("End");
        endButton.setBackground(Color.RED);
        endButton.setForeground(Color.WHITE);
        endButton.setAlignmentX(CENTER_ALIGNMENT);
        endButton.setVisible(false);
        infoPanel.add(endButton);
        endButton.addActionListener(e -> System.exit(0));

        frame.add(infoPanel, BorderLayout.NORTH);

        // add slider (speed slider)
        JLabel speedLabel = new JLabel("Speed: ");
        JSlider speedSlider = new JSlider(50, 1000, 200);
        buttonPanel.add(speedLabel);
        buttonPanel.add(speedSlider);
        speedSlider.setInverted(true);

        // Animation timer
        this.timer = new Timer(50, e -> { // Triggers code at fixed interval (50 ms)
            map.update(); // Advances simulation: expands wave, check obj destruction, update voids
            repaint(); // Triggers paintComponent(Graphics g) to redraw everything
            planetLabel.setText("Planets: "     + map.countPlanets());
            asteroidLabel.setText("Asteroids: " + map.countAsteroids());
            planetoidLabel.setText("Planetoids: "+ map.countPlanetoids());

            // only reset button works after sim is over
            if (map.isOver()) {
                timer.stop();
                simStatusLabel.setText("\nChain Reaction Complete");
                startButton.setEnabled(false);
                pauseButton.setEnabled(false);
                tickButton.setEnabled(false);
                endButton.setVisible(true);
            }
        });

        // Restart Button
        restartButton.addActionListener(e -> { // Restart
            //regenerate stars
            genStars();
            //replace solar system
            map.reStart();
            simStatusLabel.setText(" ");
            startButton.setEnabled(true);
            pauseButton.setEnabled(true);
            tickButton.setEnabled(true);
            endButton.setVisible(false);
            timer.start();  //needs to be started or does not display new solar system
            paused = true;
            pauseButton.setText("Pause");
            started = false;
            //update solar system name here
//            solarSystemName.setText(//randomName.generate()));    // ----- ----- ----- -----
        });

        // Button Actions
        startButton.addActionListener(e -> { // Button to start the program
            if (started){
                map.start();
            } else {
                started = true;
                map.start(); // Calls start on DestructionMap, initializes first wave at random planetoid
                if (paused) {
                    timer.start();
                    paused = false;
                }
            }
        });

        pauseButton.addActionListener(e -> { // Pauses
            if (!started){
                return;
            }
            if (paused){
                timer.start();
                paused = false;
                pauseButton.setText("Pause");
            } else{
                timer.stop();
                paused = true;
                pauseButton.setText("Resume");

            }
        });

        //resumeButton.addActionListener(e -> { // Play/Resume
        //});


        tickButton.addActionListener(e -> { // Ticks over ONE frame
            if (paused) {
                map.update();
                repaint();
            }
        });

        speedSlider.addChangeListener(e -> {
            // get current value and then change time interval
            int scale = speedSlider.getValue();
            timer.setDelay(scale);
            if (scale <= 200) speedLabel.setText("Speed: Fast");
            else if (scale <= 600) speedLabel.setText("Speed: Medium");
            else speedLabel.setText("Speed: Slow");

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
//       map.start(); // Calls start on DestructionMap, initializes first wave at random planetoid
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

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
    }

    // ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

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