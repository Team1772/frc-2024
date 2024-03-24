package frc.core.util;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class LedStrip {
  AddressableLED led;
  AddressableLEDBuffer ledBuffer;
  int rainbowFirstPixelHue;
  public static int identifier;
  private final int ledLength = 60;
  private final boolean isInfraredIntake = RobotContainer.intake.isInfraredSensor();
  private final boolean isLimitMaxIntake = RobotContainer.intakeMove.isLimitMax();
  private final boolean isLimitMinIntake = RobotContainer.intakeMove.isLimitMin();
  private final boolean isLimitClimber = RobotContainer.climber.isLimit();
  private final boolean isSettedVelocity = RobotContainer.shooter.atSettedVelocity();
  double batteryVoltage = RobotController.getBatteryVoltage();

  public LedStrip() {
    led = new AddressableLED(0);
    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
    ledBuffer = new AddressableLEDBuffer(ledLength);
    led.setLength(ledBuffer.getLength());
    // Set the data
    led.setData(ledBuffer);
    led.start();
  }

  public void sensorsDebugging(boolean isDebugging) {
    if (!isDebugging) {
      if (isInfraredIntake && batteryVoltage >= 12.20) {
        rgb(0, 255, 0);
      } else if (isInfraredIntake && batteryVoltage < 12.20) {
        rgb(255, 0, 0);
      } else if (isInfraredIntake) {
        rgb(100, 100, 0);
      } else if (isLimitMinIntake) {
        rgb(100, 0, 100);
      } else if (isLimitMaxIntake) {
        rgb(0, 100, 100);
      } else if (isLimitClimber) {
        rgb(255, 255, 255);
      } else {
        // m_robotContainer.led().rgb(255, 30, 0); // Laranja RSL
        rainbow();
      }
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

}
