

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class gaming extends Canvas implements Runnable{
    //Game constants
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int NANOSECONDS_PER_SECOND = 1000000000;
    private static final double NUM_TICKS = 60.0;
    private static final String Name = "Super Mario Bros";

    private static final int WINDOW_WIDTH = 960;
    private static final int WINDOW_HEIGHT = 720;

    //Game variables
    private Boolean running;

    //Game components
    private Thread thread;
    private Handler handler;
    private static Texture tex;
    private player player;
    
    public gaming() {
        initialize();
    }

    public static void main(String args[]) {
        new gaming();
    }

    public void initialize() {
        tex = new Texture();
        handler = new Handler();
        player = new player(32, 32, 1, handler); // Make sure to properly initialize this with required parameters
        handler.setPlayer(player); // Correct this method name based on actual implementation
        for (int i = 0; i < 20; i++) {
            handler.addObj(new Block(i * 32, 32 * 10, 32, 32, 1, 1));
        }
        for (int i = 0; i < 30; i ++) {
            handler.addObj(new Block(i * 32, 32*15, 32, 32, 1, 1));
        }
        
        this.addKeyListener(new KeyInput(handler));
        
        new windows(WINDOW_WIDTH, WINDOW_HEIGHT, Name, this);
    
        this.requestFocus();
    
        start();
    }
    

    private synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = NUM_TICKS;
        double ns = NANOSECONDS_PER_SECOND / amountOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            tick();

            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            if (running) {
                render();
                frames++;
            }
            

            if (System.currentTimeMillis() - timer > MILLIS_PER_SECOND) {
                timer += MILLIS_PER_SECOND;
                System.out.println("FPS: " + frames + "TPS: " + updates);
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {
        handler.tick();
        updateBackgroundPosition();
    }

    private void render() {
        BufferStrategy buf = this.getBufferStrategy();
        if (buf == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buf.getDrawGraphics();
        
        // Clear the screen with the background color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    
        // Apply camera offset by translating the Graphics context
        g.translate(backgroundOffsetX, backgroundOffsetY);
    
        // Now render all game objects; they will be drawn with the applied offset
        handler.render(g); 
    
        // Reset the translation to render UI elements or anything else that shouldn't be affected by the camera offset
        g.translate(-backgroundOffsetX, -backgroundOffsetY);
    
        g.dispose();
        buf.show();
    }
    

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static Texture getTexture() {
        return tex;
    }

    private int backgroundOffsetX = 0;
    private int backgroundOffsetY = 0;
    private void updateBackgroundPosition() {
        backgroundOffsetX = (WINDOW_WIDTH / 2) - (int)player.getX();
        backgroundOffsetY = (WINDOW_HEIGHT / 2) - (int)player.getY();
    }
}