/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aim;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author palasjiri
 */
public class ImagePanel extends JPanel{
    
    private BufferedImage image;

    public ImagePanel() {
        image = null;
    }

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        revalidate();
        repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (image == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(image.getWidth(null), image.getHeight(null));
       }
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
    
    
}
