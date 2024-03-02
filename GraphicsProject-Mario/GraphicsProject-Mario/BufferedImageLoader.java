import java.awt.image.*;
import java.io.IOException;
import javax.imageio.*;

public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) {
        try{
            image = ImageIO.read(getClass().getResource(path));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
