package fractalNoisePic;

import static fractalNoisePic.ShowImageWindow.showImageWindow;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main {
  private static final int IMAGE_WIDTH = 1920;
  private static final int IMAGE_HEIGHT = 1080;

  public static void main(String [] args) {
    // Генерация шума
    BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

    FractalNoise fractalNoise = new FractalNoise(512,
        new Random(image.getWidth(),100000),9);

    int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    int pixelIndex = 0;
    for (int i = 0; i < image.getHeight(); ++i) {
      for (int j = 0; j < image.getWidth(); ++j) {
        int value = 0xff & (int)(fractalNoise.getValue(j, i) * 255);
        pixels[pixelIndex++] = 0xff000000 | value << 16 | value << 8 | value;
      }
    }
    // Создание неба
    BufferedImage skyImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT / 2, BufferedImage.TYPE_INT_RGB);
    int skyColor = 0xFF87CEFA;
    for (int i = 0; i < skyImage.getHeight(); ++i) {
      for (int j = 0; j < skyImage.getWidth(); ++j) {
        skyImage.setRGB(j, i, skyColor);
      }
    }
    // Преобразование карты высот в горный ландшафт
    BufferedImage terrainImage;
    terrainImage = TerrainRenderer.renderTerrain(image);

    // Объединение неба и горного ландшафта
    BufferedImage finalImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT /2, BufferedImage.TYPE_INT_RGB);
    finalImage.getGraphics().drawImage(skyImage, 0, 0, null);
    finalImage.getGraphics().drawImage(terrainImage, 0, IMAGE_HEIGHT / 2, null);

    //output:
    showImageWindow(finalImage, IMAGE_WIDTH, IMAGE_HEIGHT);
  }
}
