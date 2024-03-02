import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject{

    public Block(int x, int y, int width, int height, int index, int scale) {
        super(x, y, ObjectID.Block, height, width, scale);
        this.index = index;
        sprite = tex.getTile1();
    }

    private Texture tex = gaming.getTexture();
    private int index;
    private BufferedImage[] sprite;

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(),
        (int) getHeight());
    }
}
