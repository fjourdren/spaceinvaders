package fr.space.classes;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class RessourceLoader {

    public static BufferedImage loadBufferedImage(String pathImage) {
        BufferedImage image = null;

        pathImage = "../Ressources/" + pathImage;

        try {
            if (RessourceLoader.class.getResource(pathImage) == null) {
                throw new RuntimeException("Can't find resource: " + pathImage);
            }

            image = ImageIO.read(RessourceLoader.class.getResourceAsStream(pathImage));


        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        return image;

    }
}
