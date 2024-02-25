package frc.robot.constants;

public class ShooterConstants {
  public static final int motorUpperPort = 5;
  public static final int motorLowerPort = 6;

  public static final double speed = 0.7;
  public static final boolean isInverted = true;

  public static final double wheelRadiusMeters = 0.0508;

  public static final int maxRPM = 5666,
      kSensorUnitsPerRotation = 4096;

  public static final boolean isMotorUpperInverted = false,
      isMotorLowerInverted = true,
      isSensorPhase = true;

  public static final class PID {
    public static final double kPVelocity = 0.34,
        kIVelocity = 0.0001,
        kDVelocity = 4,
        kFVelocity = 0.0319,
        kPeakOutputVelocity = 1,
        dutyCycle = 0.8;

    public static final int kIZoneVelocity = 0;
  }

}
