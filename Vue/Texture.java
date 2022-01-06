package com.projetpo.bindingofisaac.module.Vue;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.stb.STBImage.*;

/**
 * This class represents a texture.
 *
 * @author Heiko Brumme
 */
public class Texture {

    /**
     * Stores the handle of the texture.
     */
    private final int id;

    /**
     * Width of the texture.
     */
    private int width;
    /**
     * Height of the texture.
     */
    private int height;

    /** Creates a texture. */
    
    public static Texture animationCote1Isaac = loadTexture("src/main/resources/animationCote1Isaac.png");
    public static Texture animationCote2Isaac = loadTexture("src/main/resources/animationCote2Isaac.png");
    public static Texture animationCote3Isaac = loadTexture("src/main/resources/animationCote3Isaac.png");
    public static Texture animationCote4Isaac = loadTexture("src/main/resources/animationCote4Isaac.png");
    public static Texture animationCote5Isaac = loadTexture("src/main/resources/animationCote5Isaac.png");
    public static Texture animationCote6Isaac = loadTexture("src/main/resources/animationCote6Isaac.png");
    public static Texture animationCote7Isaac = loadTexture("src/main/resources/animationCote7Isaac.png");
    public static Texture animationCote8Isaac = loadTexture("src/main/resources/animationCote8Isaac.png");
    public static Texture animationCote9Isaac = loadTexture("src/main/resources/animationCote9Isaac.png");
    public static Texture animationCote10Isaac = loadTexture("src/main/resources/animationCote10Isaac.png");
    public static Texture animation2Isaac = loadTexture("src/main/resources/animation2Isaac.png");
    public static Texture animation3Isaac = loadTexture("src/main/resources/animation3Isaac.png");
    public static Texture animation4Isaac = loadTexture("src/main/resources/animation4Isaac.png");
    public static Texture animation5Isaac = loadTexture("src/main/resources/animation5Isaac.png");
    public static Texture animation6Isaac = loadTexture("src/main/resources/animation6Isaac.png");
    public static Texture animation7Isaac = loadTexture("src/main/resources/animation7Isaac.png");
    public static Texture animation8Isaac = loadTexture("src/main/resources/animation8Isaac.png");
    public static Texture animation9Isaac = loadTexture("src/main/resources/animation9Isaac.png");
    public static Texture animation10Isaac = loadTexture("src/main/resources/animation10Isaac.png");
    public static Texture bgMenu = loadTexture("src/main/resources/intro_bg.png");
    public static Texture IsaacShotUp = loadTexture("src/main/resources/IsaacShot.png");
    public static Texture IsaacShotR = loadTexture("src/main/resources/IsaacShotCoteL.png");
    public static Texture IsaacShotL = loadTexture("src/main/resources/IsaacShotCoteR.png");
    public static Texture IsaacShotDown = loadTexture("src/main/resources/IsaacShotDown.png");
    public static Texture IsaacMenu = loadTexture("src/main/resources/isaacMenu.png");
    public static Texture IsaacString = loadTexture("src/main/resources/isaacString.png");
    public static Texture MagdaleneMenu = loadTexture("src/main/resources/magdaleneMenu.png");
    public static Texture MagdaleneString = loadTexture("src/main/resources/magdaleneString.png");
    public static Texture MagdaleneUp = loadTexture("src/main/resources/MagdaleneUp.png");
    public static Texture MagdaleneDown = loadTexture("src/main/resources/MagdaleneDown.png");
    public static Texture MagdaleneR = loadTexture("src/main/resources/MagdaleneL.png");
    public static Texture MagdaleneL = loadTexture("src/main/resources/MagdaleneR.png");
    public static Texture leftArrow = loadTexture("src/main/resources/leftArrow.png");
    public static Texture rightArrow = loadTexture("src/main/resources/rightArrow.png");
    public static Texture Isaac = loadTexture("src/main/resources/Isaac.png");
    public static Texture tears = loadTexture("src/main/resources/tear.png");
    public static Texture coinBG = loadTexture("src/main/resources/coinBG.png");
    public static Texture coinBD = loadTexture("src/main/resources/coinBD.png");
    public static Texture coinHG = loadTexture("src/main/resources/coinHG.png");
    public static Texture coinHD = loadTexture("src/main/resources/coinHD.png");
    public static Texture murDroite = loadTexture("src/main/resources/murDroite.png");
    public static Texture murGauche = loadTexture("src/main/resources/murGauche.png");
    public static Texture murHaut = loadTexture("src/main/resources/murHaut.png");
    public static Texture murBas = loadTexture("src/main/resources/murBas.png");
    public static Texture rock = loadTexture("src/main/resources/Rock.png");
    public static Texture closeDoor_up = loadTexture("src/main/resources/closed_door_up.png");
    public static Texture closeDoor_down = loadTexture("src/main/resources/closed_door_down.png");
    public static Texture closeDoor_right = loadTexture("src/main/resources/closed_door_right.png");
    public static Texture closeDoor_left = loadTexture("src/main/resources/closed_door_left.png");
    public static Texture top_openDoor = loadTexture("src/main/resources/top_opened_door.png");
    public static Texture bot_openDoor = loadTexture("src/main/resources/bot_opened_door.png");
    public static Texture right_openDoor = loadTexture("src/main/resources/right_opened_door.png");
    public static Texture left_openDoor = loadTexture("src/main/resources/left_opened_door.png");
    public static Texture spikes = loadTexture("src/main/resources/Spikes.png");
    public static Texture heart = loadTexture("src/main/resources/HUD_heart_red_full.png");
    public static Texture halfHeart = loadTexture("src/main/resources/HUD_heart_red_half.png");
    public static Texture emptyHeart = loadTexture("src/main/resources/HUD_heart_red_empty.png");
    public static Texture gameOver = loadTexture("src/main/resources/lose.png");
    public static Texture fly = loadTexture("src/main/resources/fly.png");  
    public static Texture boss1 = loadTexture("src/main/resources/boss1.png");
    public static Texture boss2 = loadTexture("src/main/resources/boss2.png");
    public static Texture right = loadTexture("src/main/resources/right.png");
    public static Texture top_bot_isaac = loadTexture("src/main/resources/top_bot_isaac.png");
    public static Texture bas = loadTexture("src/main/resources/bas.png");
    public static Texture haut = loadTexture("src/main/resources/haut.png");
    public static Texture left = loadTexture("src/main/resources/left.png");
    public static Texture left_right_isaac = loadTexture("src/main/resources/left_right_isaac.png");
    public static Texture spawnDraw = loadTexture("src/main/resources/spawnDraw.png");
    public static Texture txtZero = loadTexture("src/main/resources/txt_zero.png");
    public static Texture txtUn = loadTexture("src/main/resources/txt_un.png");
    public static Texture txtDeux = loadTexture("src/main/resources/txt_deux.png");
    public static Texture txtTrois = loadTexture("src/main/resources/txt_trois.png");
    public static Texture txtQuatre = loadTexture("src/main/resources/txt_quatre.png");
    public static Texture txtCinq = loadTexture("src/main/resources/txt_cinq.png");
    public static Texture txtSix = loadTexture("src/main/resources/txt_six.png");
    public static Texture txtSept = loadTexture("src/main/resources/txt_sept.png");
    public static Texture txtHuit = loadTexture("src/main/resources/txt_huit.png");
    public static Texture txtNeuf = loadTexture("src/main/resources/txt_neuf.png");
    public static Texture txtCoin = loadTexture("src/main/resources/txt_coin.png");
    public static Texture txtX = loadTexture("src/main/resources/txt_x.png");
    public static Texture txtDot = loadTexture("src/main/resources/txt_dot.png");
    public static Texture emptyCell = loadTexture("src/main/resources/emptyCell.png");  

    public Texture() {
        id = glGenTextures();
    }

    /**
     * Binds the texture.
     */
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    /**
     * Unbinds the texture.
     */
    public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

    /**
     * Sets a parameter of the texture.
     *
     * @param name  Name of the parameter
     * @param value Value to set
     */
    public void setParameter(int name, int value) {
        glTexParameteri(GL_TEXTURE_2D, name, value);
    }

    /**
     * Uploads image data with specified width and height.
     *
     * @param width  Width of the image
     * @param height Height of the image
     * @param data   Pixel data of the image
     */
    public void uploadData(int width, int height, ByteBuffer data) {
        uploadData(GL_RGBA8, width, height, GL_RGBA, data);
    }

    /**
     * Uploads image data with specified internal format, width, height and
     * image format.
     *
     * @param internalFormat Internal format of the image data
     * @param width          Width of the image
     * @param height         Height of the image
     * @param format         Format of the image data
     * @param data           Pixel data of the image
     */
    public void uploadData(int internalFormat, int width, int height, int format, ByteBuffer data) {
        glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, GL_UNSIGNED_BYTE, data);
    }

    /**
     * Delete the texture.
     */
    public void delete() {
        glDeleteTextures(id);
    }

    /**
     * Gets the texture width.
     *
     * @return Texture width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the texture width.
     *
     * @param width The width to set
     */
    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        }
    }

    /**
     * Gets the texture height.
     *
     * @return Texture height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the texture height.
     *
     * @param height The height to set
     */
    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        }
    }

    /**
     * Creates a texture with specified width, height and data.
     *
     * @param width  Width of the texture
     * @param height Height of the texture
     * @param data   Picture Data in RGBA format
     *
     * @return Texture from the specified data
     */
    public static Texture createTexture(int width, int height, ByteBuffer data) {
        Texture texture = new Texture();
        texture.setWidth(width);
        texture.setHeight(height);

        texture.bind();

        texture.setParameter(GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
        texture.setParameter(GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
        texture.setParameter(GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        texture.setParameter(GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        texture.uploadData(GL_RGBA8, width, height, GL_RGBA, data);
        
        return texture;
    }

    /**
     * Load texture from file.
     *
     * @param path File path of the texture
     *
     * @return Texture from specified file
     */
    public static Texture loadTexture(String path) {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            /* Prepare image buffers */
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            /* Load image */
            stbi_set_flip_vertically_on_load(true);
            image = stbi_load(path, w, h, comp, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load a texture file!"
                                           + System.lineSeparator() + stbi_failure_reason());
            }

            /* Get width and height of image */
            width = w.get();
            height = h.get();
        }
        return createTexture(width, height, image);
    }

}