import java.awt.image.*;
import java.io.IOException;
import javax.imageio.*;

public class Texture {
    //This is where we put the file that contains the images for tile look at video 19/29 o the playlist
    private final String PARENT_FOLDER = "testing-pics";

    private final int MARIO_L_COUNT = 13;
    private final int MARIO_S_COUNT = 15;

    private final int TILE1_COUNT = 8;
    private final int TILE2_COUNT = 8;

    private BufferedImageLoader loader;
    private BufferedImage player_sheet, enemy_sheet, npm_sheet, block_sheet,
    tile_sheet, game_over_sheet, intro_sheet;
    public BufferedImage[] mario1, mario_s, tile_1, tile_2, tile_3, tile_4, pipe_1;

    public Texture() {
        mario1 = new BufferedImage[MARIO_L_COUNT];
        mario_s = new BufferedImage[MARIO_S_COUNT];
        tile_1 = new BufferedImage[TILE1_COUNT + TILE2_COUNT];
        tile_2 = new BufferedImage[TILE1_COUNT + TILE2_COUNT];
        tile_3 = new BufferedImage[TILE1_COUNT + TILE2_COUNT];
        tile_4 = new BufferedImage[TILE1_COUNT + TILE2_COUNT];
        pipe_1 = new BufferedImage[4];

        loader = new BufferedImageLoader();

        try {
            player_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Mario & Luigi.png");
            enemy_sheet = loader.loadImage(PARENT_FOLDER + "/enemies.png");
            npm_sheet = loader.loadImage(PARENT_FOLDER + "/characters.gif");
            block_sheet = loader.loadImage(PARENT_FOLDER + "/blocks.png");
            tile_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Tileset.png");
            game_over_sheet = loader.loadImage(PARENT_FOLDER + "/gameover.png");
            intro_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Title Screen HUD and Miscellaneous.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getPlayerTextures();
        getTileTextures();
        getPipeTextures();
    }

    private void getPlayerTextures() {
        int x_off = 0;
        int y_off = 8;
        int width = 16;
        int height = 16;

        for (int i = 0; i < MARIO_S_COUNT; i++) {
            if (i == 1 || i == 4 || i == 5 || i == 6 || i == 7 || i == 9) {
                width = 18; 
            } else {
                width = 16;
            }
            mario_s[i] = player_sheet.getSubimage(x_off + i*(width + 2), y_off, width, height);
        }

        y_off = 32;
        height = 32;
        for (int i = 0; i < MARIO_L_COUNT; i++) {
            if (i == 1 || i == 4 || i == 5 || i == 6 || i == 7 || i == 9) {
                width = 18; // Adjust for the 4-pixel gap
            } else {
                width = 16;
            }
            mario1[i] = player_sheet.getSubimage(x_off + i*(width + 2), y_off, width, height);
        }
    }

    private void getTileTextures() {
        int x_off = 0; // Starting x offset
        int y_off = 16; // Starting y offset for first row
        int width = 16; // Width of a single tile
        int height = 16; // Height of a single tile
    
        // Load the first set of tiles
        for (int j = 0; j < TILE1_COUNT; j++) {
            tile_1[j] = tile_sheet.getSubimage(x_off + j * (width + 1), y_off, width, height);
        }
    
        y_off += height + 1; // Move to the next row for second set of tiles
    
        // Load the second set of tiles
        for (int j = 0; j < TILE2_COUNT; j++) {
            tile_1[j + TILE1_COUNT] = tile_sheet.getSubimage(x_off + j * (width + 1), y_off, width, height);
        }
    
        // Adjust y_off for tile_2 if needed, and then similarly load tile_2 arrays
        // Assuming tile_2 starts on the same row as tile_1 but after TILE1_COUNT tiles
        x_off = (TILE1_COUNT * (width + 1)); // New x offset for tile_2
        y_off = 16; // Reset y offset for the first row of tile_2
    
        for (int j = 0; j < TILE1_COUNT + TILE2_COUNT; j++) {
            if (j >= TILE1_COUNT) {
                // Adjust y_off when you move to the second set of tiles in tile_2 array
                y_off += height + 1;
            }
            tile_2[j] = tile_sheet.getSubimage(x_off + j * (width + 1), y_off, width, height);
        }
    }    
    private void getPipeTextures() {
        int x_off = 119;
        int y_off = 196;
        int width = 16;
        int height = 16;

        pipe_1[0] = tile_sheet.getSubimage(x_off, y_off, width, height);
        pipe_1[1] = tile_sheet.getSubimage(x_off + 17, y_off, width, height);
        pipe_1[2] = tile_sheet.getSubimage(x_off, y_off + 17, width, height);
        pipe_1[3] = tile_sheet.getSubimage(x_off + 17, y_off + 17, width, height);
    }
    public BufferedImage[] getMarioL() {
        return mario1;
    }
    public BufferedImage[] getMarioS() {
        return mario_s;
    }
    public BufferedImage[] getTile1() {
        return tile_1;
    }
    public BufferedImage[] getTile2() {
        return tile_2;
    }
    public BufferedImage[] getTile3() {
        return tile_3;
    }
    public BufferedImage[] getTile4() {
        return tile_4;
    }
    public BufferedImage[] getPipe() {
        return pipe_1;
    }
}
