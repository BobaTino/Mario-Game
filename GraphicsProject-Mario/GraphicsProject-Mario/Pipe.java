import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Pipe extends GameObject{
    private boolean enterable;
    private Texture tex = gaming.getTexture();
    private int index;
    private BufferedImage sprite[];

    public Pipe(int x, int y, int height, int width, int scale, int index, boolean enterable) {
        super(x, y, ObjectID.Pipe, width, height, scale);
        this.enterable = enterable;
        this.index = index;
        sprite = tex.getPipe();
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}
