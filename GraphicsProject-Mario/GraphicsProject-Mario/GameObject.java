import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    private float x;
    private float y;
    private ObjectID id;
    private float velX, velY;
    private float width, height;
    private int scale;

    public GameObject(float x, float y, ObjectID id, float width, float height, int scale) {
        this.x = x * scale;
        this.y = y * scale;
        this.id = id;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void applyGravity() {
        velY += 0.5f;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setId(ObjectID id) {
        this.id = id;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setWidth(float width) {
        this.width = width * scale;
    }

    public void setHeight(float height) {
        this.height = height * scale;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ObjectID getId() {
        return id;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY(){
        return velY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
