package frc.robot.constants;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class DrivetrainConstants {
  public static final class Motors {
    public static final int
      motorLeftBack = 1,
      motorLeftFront = 2,
      motorRightBack = 3,
      motorRightFront = 4;

    public static final boolean 
      isMotorsLeftInverted = true,
      isMotorsRightInverted = false;
  }

  public static final class Encoders {
    public static final int
      encoderLeftPortOne = 6,
      encoderLeftPortTwo = 7,
      encoderRightPortOne = 8,
      encoderRightPortTwo = 9;

    public static final boolean
      isEncoderLeftInverted = true,
      isEncoderRightInverted = true;
  
    public static final int
      pulsesPerRotation = 500, 
      cyclesPerRevolution = pulsesPerRotation * 4;  
  }

  public static final class Chassi {
    public static final int 
    wheelRadius = 2;
  }

  public static final class Gains {
    public static final double 
    ksVolts = 1, //kS
    kvVoltSecondsPerMeter = 2.50, //kV
    kaVoltSecondsSquaredPerMeter = 0.92, //kA
    kTrackwidthMeters = 0.80,
    differentialDriveVoltageConstraintMaxVoltage = 7;

    public static final DifferentialDriveKinematics
      kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);
  }
  
  public static final class PID {
    public static final double 
      kPDriveVelocity = 4.08,
      kIDriveVelocity = 0,
      kDDriveVelocity = 0;
  }

  public final class Autonomous {
    public static final double 
      kMaxSpeedMetersPerSecond = 2,
      kMaxAccelerationMetersPerSecondSquared = 2,
      kRamseteB = 2,
      kRamseteZeta = 0.7;
  }
}