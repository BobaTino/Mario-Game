import java.util.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
public class game implements Runnable{

    //GAME CONSTANTS 
    private static final int MILLIS_PER_SECOND = 1000; //This will be used to convert seconds to milliseconds
    private static final int NANOSECONDS_PER_SECOND = 1000000000; //This will be used to convert seconds to nanoseconds
    private static final double NUM_TICKS = 60.0; //This will be used to determine how many times the game will update per second
    

    //GAME VARIABLES
    private boolean gameState; //This will whether the game is running or not

    //GAME COMPONENTS  
    private Thread inGameLoop; //This will be the thread that will run the game 
    public game() { //Constructor of game class
        initialize();
    }
    public static void main(String[] args) {
        
        new game();
    }

    public void initialize() {
        start();
    }

    /* What is synchronized?
    * Synchronized is a keyword in Java that is used to control access to a block of code.
    * When a method is synchronized, only one thread can access the method at a time.
    * This is useful when multiple threads are trying to access the same method.
    * In this case, we are using synchronized to control access to the start and stop methods.
    * This is because we want to make sure that only one thread can access these methods at a time.
    * This is important because we don't want to start or stop the game while it is already running.
    * If we did, it could cause the game to crash or behave unexpectedly.
    */

    private synchronized void start() {
        inGameLoop = new Thread(this);
        inGameLoop.start();

        gameState = true;
        
    }

    private synchronized void stop(){

        try {
            inGameLoop.join();
            gameState = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        

    }

    @Override
    public void run() {


        long lastTime = System.nanoTime(); //This will store the current time in nanoseconds 
        double amountOfTicks = NUM_TICKS; //This will store the amount of updates per second
        double ns = NANOSECONDS_PER_SECOND / amountOfTicks; //This will store the amount of nanoseconds per tick
        double delta = 0; //This will store the amount of time that has passed
        long timer = System.currentTimeMillis(); //This will store the current time in milliseconds
        int frames = 0; //This will store the amount of frames per second
        int updates = 0; //This will store the amount of updates per second

        while (gameState) {
            long now = System.nanoTime(); //This will store the current time in nanoseconds
            delta += (now - lastTime) / ns; //This will store the amount of time that has passed
            lastTime = now; //This will store the current time in nanoseconds
           
            while (delta >= 1) {
                tick(); //This will update the game
                updates++; //This will increase the amount of updates per second
                delta--; //This will decrease the amount of time that has passed
            }
            if(gameState) {
                render(); //This will render the game
                frames++; //This will increase the amount of frames per second
            }
            if (System.currentTimeMillis() - timer > MILLIS_PER_SECOND) {
                timer += MILLIS_PER_SECOND; //This will store the current time in milliseconds
                System.out.println("FPS: " + updates + ", Frames(TPS): " + frames); //This will print the amount of updates and frames per second
                updates = 0; //This will reset the amount of updates per second
                frames = 0; //This will reset the amount of frames per second
            }
        }
        stop();
    }
    private void tick() {
        //This is where the game will update
        
    }

    private void render() {
        //This is where the game will render
    }

    public static class DrawBackground implements Runnable {
        public void run()
        {
            while (!endgame)
            {
                setBackground();
            }
        }
    }

    private static void setBackground()
    {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, XOFFSET, YOFFSET, null);
    }
    public static class PlayerMover implements Runnable {
        public PlayerMover()
        {
            velocity = 1.2;
        }
        public void run()
        {
            while (!player1Active || !player2Active)
            {
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException e) {}
                if (leftMovement || rightMovement)
                {
                    if (player1Active)
                    {
                        if (leftMovement) player1Velocity += velocity; leftMovement = false;
                        if (rightMovement) player1Velocity += velocity; rightMovement = false;
                    }
                    if (player2Active)
                    {
                        if (leftMovement) player2Velocity += velocity; leftMovement = false;
                        if (rightMovement) player2Velocity += velocity; rightMovement = false;
                    }
                }
            }
        }
        private double velocity;
    }

    private static class StartGame implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            Thread t1 = new Thread(new PlayerMover());
            t1.start();
        }
    }

    public class ImageObject {

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public int getWidth()
        {
            return width;
        }

        public int getHeight()
        {
            return height;
        }

        private int x;
        private int y;
        private int width;
        private int height;
    }

    private static BufferedImage background;
    private static BufferedImage itemBlock;
    private static ImageObject player1;
    private static ImageObject player2;
    private static ImageObject enemy;
    private static Vector<ImageObject> enemies;

    private static boolean player1Active = false;
    private static boolean player2Active = false;
    private static boolean endgame;

    private static double player1Velocity;
    private static double player2Velocity;

    private static int XOFFSET;
    private static int YOFFSET;

    private static boolean leftMovement;
    private static boolean rightMovement;

    private static JFrame appFrame;
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
}