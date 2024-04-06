package frc.core.util;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LedStrip {
  private static LedStrip uniqueInstance;

  AddressableLED led;
  AddressableLEDBuffer ledBuffer;
  int rainbowFirstPixelHue;
  public int currentIdentifier;
  public int previousIdentifier;

  // Default to a length of 60, start empty output
  private final int ledLength = 60;

  private LedStrip() {
    led = new AddressableLED(0);
    ledBuffer = new AddressableLEDBuffer(ledLength);
    led.setLength(ledBuffer.getLength());
    led.setData(ledBuffer);
    led.start();
  }

  public void setIdentifier(int identifier) {
    previousIdentifier = currentIdentifier;
    currentIdentifier = identifier;
  }

  public void sensorsDebugging(boolean isDebugging) {
    if (!isDebugging) {
      setColor(0);
    } else {
      if (currentIdentifier != previousIdentifier) {
        setColor(currentIdentifier);
      }
    }

  }

  private void setColor(int identifier) {
    switch (identifier) {
      case 0:
        rainbow();
        break;
      case 1:
        rgb(0, 255, 0);
        break;
      case 2:
        rgb(255, 0, 0);
        break;
      case 3:
        rgb(100, 100, 0);
        break;
      case 4:
        rgb(100, 0, 100);
        break;
      case 5:
        rgb(0, 100, 100);
        break;
      case 6:
        rgb(255, 255, 0);
        break;
      case 7:
        rgb(180, 180, 0);
        break;
      default:
        rgb(255, 255, 255);
        break;
    }
  }

  public void rgb(int r, int g, int b) {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, r, g, b);
    }

    led.setData(ledBuffer);
  }

  public void rainbow() {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
      ledBuffer.setHSV(i, hue, 255, 128);
    }
    rainbowFirstPixelHue += 3;
    rainbowFirstPixelHue %= 180;

    led.setData(ledBuffer);

  }

  public void setData() {
    led.setData(ledBuffer);
  }

  public static synchronized LedStrip getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new LedStrip();
    }
    return uniqueInstance;
  }

}
