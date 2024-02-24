package frc.robot.constants;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class DrivetrainConstants {
  public static final class Motors {
    public static final int motorLeftBack = 1,
        motorLeftFront = 2,
        motorRightBack = 3,
        motorRightFront = 4;

    public static final boolean isMotorsLeftInverted = true,
        isMotorsRightInverted = false;
  }

  public static final class Encoders {
    public static final int encoderLeftPortOne = 3,
        encoderLeftPortTwo = 4,
        encoderRightPortOne = 8,
        encoderRightPortTwo = 9;

    public static final boolean isEncoderLeftInverted = false,
        isEncoderRightInverted = false;

    public static final int pulsesPerRotation = 500,
        cyclesPerRevolution = pulsesPerRotation * 4;
  }

  public static final class Chassi {
    public static final int wheelRadius = 2;
  }

  public static final class Gains {
    public static final double ksVolts = 1.2435, // kS
        kvVoltSecondsPerMeter = 2.5724, // kV
        kaVoltSecondsSquaredPerMeter = 0.87055, // kA
        kTrackwidthMeters = 0.80,
        differentialDriveVoltageConstraintMaxVoltage = 7;

    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
        kTrackwidthMeters);
  }

  public static final class PID {
    public static final double kPDriveVelocity = 2.7046,
        kIDriveVelocity = 0,
        kDDriveVelocity = 0;
  }

  public final class Autonomous {
    public static final double kMaxSpeedMetersPerSecond = 2,
        kMaxAccelerationMetersPerSecondSquared = 2,
        kRamseteB = 2,
        kRamseteZeta = 0.7;
  }

  public final class SysId {
    public static final boolean isSysIdTunning = false;
  }
}