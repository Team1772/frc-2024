package frc.core.util;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.RobotController;

public class Led {
  private static final int LEDS_SIZE = 16;
  static AddressableLED led;
  static AddressableLEDBuffer ledBuffer;
  static int rainbowFirstPixelHue;
  static int currentIntermitentValue = 0;

  public Led() {
    led = new AddressableLED(0);

    ledBuffer = new AddressableLEDBuffer(LEDS_SIZE);
    led.setLength(ledBuffer.getLength());
    Led.disable();
    led.setData(ledBuffer);
    led.start();
  }

  public static void set(int color) {
    if (RobotController.getBatteryVoltage() < 13) {
      Led.setIntermitentRedColor();
      return;
    } 

    switch(color) {
      case 0:
        Led.setCyanColor();
        break;
      case 1:
        Led.setYellowColor();
        break;
    }
  }

  public static void rgb(int r, int g, int b) {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, r, g, b);
    }

    led.setData(ledBuffer);
  }

  public static void rgbIntermitent(int r, int g, int b) {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, r, g, b);
    }

    currentIntermitentValue+=4;


    if (currentIntermitentValue > 60) {
      Led.disable();

      if (currentIntermitentValue > 18) currentIntermitentValue -=9;
      return;
    }
  
    led.setData(ledBuffer);
  }


  public static void setYellowColor() {
    Led.rgb(100, 100, 0);
  }

  public void setPurpleColor() {
    Led.rgb(100, 0, 100);
  }

  public static void setCyanColor() {
    Led.rgb(0, 100, 100);
  }

  public static void setRedColor() {
    Led.rgb(255, 0, 0);
  }

  public static void setIntermitentRedColor() {
    Led.rgbIntermitent(255, 0, 0);
  }

  public static void setWhiteColor() {
    Led.rgb(255, 255, 255);
  }

  public static void disable() {
    Led.rgb(0, 0, 0);
  }

  public static void rainbow() {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
      ledBuffer.setHSV(i, hue, 255, 128);
    }
    rainbowFirstPixelHue += 3;
    rainbowFirstPixelHue %= 180;

    led.setData(ledBuffer);
  }

  public void setDataLed() {
    led.setData(ledBuffer);
  }
}
