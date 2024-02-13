package frc.robot.constants;

public class ShooterConstants {
  public static final int motorPort = 5;
  public static final int motorFollowerPort = 6;

  public static final double speed = 0.53;
  public static final boolean isInverted = true;

  public static final double wheelRadiusMeters = 0.0508;

  public static final int maxRPM = 5666,
      kSensorUnitsPerRotation = 4096;

  public static final boolean isMotorInverted = false,
      isMotorFollowerInverted = true,
      isSensorPhase = true;

  public static final class PID {
    public static final double kPVelocity = 0.8,
        kIVelocity = 0,
        kDVelocity = 8,
        kFVelocity = 0.0319,
        kPeakOutputVelocity = 1,
        dutyCycle = 0.8;

    public static final int kIZoneVelocity = 0;
  }

}
