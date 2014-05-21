/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aim;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author palasjiri
 */
public class ImageProcessing extends JPanel implements ActionListener {

    private final JToolBar toolBar;
    private final ImagePanel sourcePanel;
    private final ImagePanel processedPanel;

    private GrayImage image;
    private GrayImage processed;

    private final JButton saveBtn, loadBtn, applyBtn;
    JTextField sigmaDtf, sigmaRtf;

    BilateralFilter bf = new BilateralFilter(12, 6);

    public ImageProcessing() {
        super(new BorderLayout());

        processedPanel = new ImagePanel();
        sourcePanel = new ImagePanel();

        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setFloatable(false);
        loadBtn = new JButton("Load");
        loadBtn.addActionListener(this);
        toolBar.add(loadBtn);
        
        toolBar.add(new JLabel("Sigma D:"));
        sigmaDtf = new JTextField(String.valueOf(bf.getSigmaD()));
        toolBar.add(sigmaDtf);

        toolBar.add(new JLabel("Sigma R:"));
        sigmaRtf = new JTextField(String.valueOf(bf.getSigmaR()));
        toolBar.add(sigmaRtf);

        applyBtn = new JButton("Apply!");
        applyBtn.addActionListener(this);
        toolBar.add(applyBtn);

        saveBtn = new JButton("Save");
        saveBtn.addActionListener(this);
        toolBar.add(saveBtn);

        add(toolBar, BorderLayout.NORTH);
        add(sourcePanel, BorderLayout.WEST);
        add(processedPanel, BorderLayout.EAST);
    }

    public void loadImage(File file) {
        image = ImageLoader.loadImage(file);
        sourcePanel.setImage(image.getBufferedImage());
    }

    public void saveImage() {
        String name;
    }

    public void applyBilateralFilter() {
        processed = bf.bruteForce(image);
        processedPanel.setImage(processed.getBufferedImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loadBtn)) {
            System.out.println("Load performed!");
            JFileChooser fc = new JFileChooser();

            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println("Opening: " + file.getName() + ".");
                loadImage(file);
            } else {
                System.out.println("Open command cancelled by user.");
            }
        } else if (e.getSource().equals(saveBtn)) {
            String format = "png";
            File saveFile = new File("savedimage." + format);
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(saveFile);
            int rval = chooser.showSaveDialog(this);

            if (rval == JFileChooser.APPROVE_OPTION) {
                saveFile = chooser.getSelectedFile();
                /* Write the filtered image in the selected format,
                 * to the file chosen by the user.
                 */
                try {
                    ImageIO.write(processed.getBufferedImage(), format, saveFile);
                } catch (IOException ex) {
                }
            }

        } else if (e.getSource().equals(applyBtn)) {
            System.out.println("Applying bilateral filter ...");
            final double d = Double.valueOf(sigmaDtf.getText());
            final double r = Double.valueOf(sigmaRtf.getText());

            bf = new BilateralFilter(d, r);

            applyBilateralFilter();
        }
    }
}
