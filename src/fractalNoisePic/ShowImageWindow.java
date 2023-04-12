package fractalNoisePic;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.*;

public class ShowImageWindow {
  public static void showImageWindow(Image image) {
    showImageWindow(image,/*width*/1024,/*height*/768);
  }

  static void showImageWindow(Image image, int width, int height) {
    JFrame frame = new JFrame();
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel picLabel = new JLabel(new ImageIcon(image));

    BorderLayout borderLayout = new BorderLayout();
    frame.getContentPane().setLayout(borderLayout);
    frame.getContentPane().add(picLabel, BorderLayout.CENTER);

frame.setVisible(true);

  }

}
