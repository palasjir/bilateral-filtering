/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aim;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author palasjiri
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ImageProcessing  imgproc = new ImageProcessing();
        
        JFrame frame =  new JFrame("Bilateral filtering");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1024, 520));
        
        frame.getContentPane().add(imgproc);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
