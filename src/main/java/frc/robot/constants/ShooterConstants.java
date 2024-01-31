package frc.robot.constants;

public class ShooterConstants {
  public static final int motorPort = 1;

  public static final double speed = 0.53;
  public static final boolean isInverted = true;

  public static final double wheelRadius = 0.1016;

  public static final int maxRPM = 6232,
      kSensorUnitsPerRotation = 4096;

  public static final boolean isMotorInverted = true,
      isSensorPhase = false;

  public static final class PID {
    public static final double kPVelocity = 0.8,
        kIVelocity = 0,
        kDVelocity = 8,
        kFVelocity = 0.0341,
        kPeakOutputVelocity = 1,
        dutyCycle = 0.8;

    public static final int kIZoneVelocity = 0;
  }

}
