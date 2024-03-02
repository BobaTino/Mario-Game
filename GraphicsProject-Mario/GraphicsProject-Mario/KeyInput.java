import java.awt.event.*;

public class KeyInput extends KeyAdapter {
    private boolean[] KeyDown = new boolean[4];
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        // jump
        if(key == KeyEvent.VK_W) {
            if (!handler.GetPlayer().hasJumped()) {
                handler.GetPlayer().setVelY(-15);
                KeyDown[0] = true;
                handler.GetPlayer().setJumped(true);
            }
        }

        if(key == KeyEvent.VK_A) {
            handler.GetPlayer().setVelX(-1);
            KeyDown[1] = true;
        }

        if(key == KeyEvent.VK_D) {
            handler.GetPlayer().setVelX(1);
            KeyDown[2] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

       if(key == KeyEvent.VK_W) {  
            KeyDown[0] = false;
        }

        if(key == KeyEvent.VK_A) {
            KeyDown[1] = false;
        }

        if(key == KeyEvent.VK_D) {
            KeyDown[2] = false;
        }
        if (!KeyDown[1] || !KeyDown[2]) {
            handler.GetPlayer().setVelX(0);
        }
    }
}
