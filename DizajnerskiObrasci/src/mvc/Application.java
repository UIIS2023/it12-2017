package mvc;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
        DrawingFrame frame = new DrawingFrame();
        
        frame.setTitle("IT12g2017 Dejana Coskov");
        frame.setSize(new Dimension(1024, 768));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.setVisible(true);
       
    }

}
