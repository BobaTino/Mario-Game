import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class player extends GameObject{
    private static final float WIDTH = 16;
    private static final float HEIGHT = 16;
    private PlayerState state;


    private Handler handler;
    private Texture tex;
    private BufferedImage[] spriteL, spriteS;
    private Animation playerWalkL, playerWalkS;
    private BufferedImage[] currSprite;
    private Animation currAnimation;


    private boolean jumped = false;
    private int health = 2;
    private boolean forward = false;


    public player(float x, float y, int scale, Handler handler) {
        super(x, y, ObjectID.Player, WIDTH, HEIGHT, scale);
        this.handler = handler;
        tex = gaming.getTexture();
        spriteL = tex.getMarioL();
        spriteS = tex.getMarioS();

        playerWalkL = new Animation(1, spriteL[1], spriteL[2], spriteL[3]);
        playerWalkS = new Animation(1, spriteS[1], spriteS[2], spriteS[3]);

        state = PlayerState.Small;
        currSprite = spriteS;
        currAnimation = playerWalkS;


    }

    @Override
    public void tick() {
        setX(getVelX() + getX());
        setY(getVelY() + getY());
        applyGravity();

        System.out.println("running");

        collision();
        currAnimation.runAnimation();

   }

    @Override
    public void render(Graphics g) {
        if(jumped) {
            if (forward) {
                g.drawImage(currSprite[5], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
            } else {
                g.drawImage(currSprite[5], (int) ((int) getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight(), null);
            }
        } else if (getVelX() > 0){
            currAnimation.drawAnimation(g, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
            forward = true;
        } else if (getVelX() < 0) {
            currAnimation.drawAnimation(g, (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
            forward = false;
        } else {
            g.drawImage(currSprite[0], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
        
        }
        
        // if (health == 1) {
        //     g.drawImage(spriteS[0], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight()/2, null);
        // } else if (health == 2){
        //     g.drawImage(spriteL[0], (int) getX(), (int) getY(), (int)getWidth(), (int) getHeight(), null);
        // }
    }

    public void collision() {
        for (int i = 0; i < handler.getGameObjs().size(); i++) {
            GameObject temp = handler.getGameObjs().get(i);

            if(temp.getId() == ObjectID.Block || temp.getId() == ObjectID.Pipe) {
                if (getBounds().intersects(temp.getBounds())) {
                    setY(temp.getY() - getHeight());
                    setVelY(0);
                    jumped = false;
                }

                if (getBoundsTop().intersects(temp.getBounds())) {
                    setY(temp.getY() - temp.getHeight());
                    setVelY(0);
                }

                if (getBoundsRight().intersects(temp.getBounds())) {
                    setX(temp.getX() - getWidth());
                }

                if (getBoundsLeft().intersects(temp.getBounds())) {
                    setX(temp.getX() - temp.getWidth());
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(getX() + getWidth() / 2 - getWidth()/4), 
        (int)(getY() + (getHeight() / 2)), (int) getWidth()/2, (int) getHeight() /2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int)(getX() + getWidth() / 2 - getWidth()/4), 
        (int)getY(), (int) getWidth()/2, (int) getHeight() /2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int)(getX() + getWidth() - 5), (int) (getY() + 5), 
        5, (int) getHeight() - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) (getX()), (int) (getY() + 5), 
        5, (int) (getHeight() - 10));
    }

    private void showBounds(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.RED);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
    }

    public Boolean hasJumped() {
        return jumped;
    }

    public void setJumped(boolean hasJumped) {
        jumped = hasJumped();
    }
}