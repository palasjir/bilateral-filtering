/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aim;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author palasjiri
 */
public class ImageLoader {

    public static GrayImage loadImage(File file) {

        BufferedImage tempImage;
        WritableRaster tempRaster;
        GrayImage image = null;
        System.out.println(file.getAbsolutePath());
        try {
            tempImage = ImageIO.read(file);
            image = new GrayImage(tempImage.getWidth(), tempImage.getWidth());
            tempRaster = tempImage.getRaster();
            
            for (int x = 0; x < tempImage.getWidth(); x++) {
                for (int y = 0; y < tempImage.getHeight(); y++) {
                    
                    image.setPixel(x, y, tempImage.getRGB(x, y));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;

    }

}
