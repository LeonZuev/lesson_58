package fractalNoisePic;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class TerrainRenderer {
  private static final float HEIGHT_SCALE = 10.0f;

  public static BufferedImage renderTerrain(BufferedImage noiseImage) {
    BufferedImage terrianImage = new BufferedImage(
            /*
            TYPE_INT_RGB - это одна из констант типов изображения,
            определенных в классе java.awt.image.BufferedImage.
             Это значение указывает, что изображение использует
             24-битный цвет без альфа-канала (прозрачность).
             */
            noiseImage.getWidth(), noiseImage.getHeight(), BufferedImage.TYPE_INT_RGB
    );

    int[] noisePixels = ((DataBufferInt) noiseImage.getRaster().getDataBuffer()).getData();
    int[] terrainPixels = ((DataBufferInt) terrianImage.getRaster().getDataBuffer()).getData();

    for (int i = 0; i < noisePixels.length; ++i) {
      float height = (noisePixels[i] & 0xFF) / 255.0f;
      int rgb = heightToColor(height);
      terrainPixels[i] = 0xFF000000 | rgb;
    }
    return terrianImage;
  }
/*
преобразуем значение высоты (из карты высот, полученной на основе шума)
в соответствующий цвет для визуализации горного пейзажа.
 */
  private static int heightToColor(float height) {
    float adjustedHeight = height * HEIGHT_SCALE;
    int r, g, b;

    if (adjustedHeight < 2.0f) {
      r = 0;
      g = 0;
      b = 255;
    } else if (adjustedHeight < 4.0f) {
      r = 0;
      g = (int) (255 * (adjustedHeight - 2.0f) / 2.0f);
      b = 255;
    } else if (adjustedHeight < 6.0f) {
      r = 0;
      g = 255;
      b = (int) (255 * (1.0f -(adjustedHeight - 4.0f) / 2.0f));
    } else {
      r = (int) (255 * (adjustedHeight - 6.0f) / 4.0f);
      g = 255;
      b = 0;
    }
    return (r << 16) | (g << 8) | b;
  }
}
