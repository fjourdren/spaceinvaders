package fr.space.Utils;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class RessourceLoader {

    /*
     * Methods
     * */
    // chargement d'un fichier depuis les ressources du programme
    public static BufferedImage loadBufferedImageFromRessources(String pathImage) {
        BufferedImage image = null;

        pathImage = "/fr/space/Ressources/" + pathImage; // on ajoute le path du dossier ressource au pathImage

        // on essaye de load l'image
        try {
            if (RessourceLoader.class.getClassLoader().getResource(pathImage) == null) { // vérifie si l'image existe
                throw new RuntimeException("Can't find resource: " + pathImage);
            }

            image = ImageIO.read(RessourceLoader.class.getResource(pathImage));


        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        return image;

    }

    // chargement d'une image depuis n'importe où sur la machine
    public static BufferedImage loadBufferedImage(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        return image;
    }
}
