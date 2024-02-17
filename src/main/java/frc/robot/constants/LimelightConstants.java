package frc.robot.constants;

public final class LimelightConstants {
  
  public static final String tableName = "limelight";
  public static final int aprilTagPipeline = 0;
  public static final int tapePipeline = 1;
  public static final int driverPipeline = 2;
  public static final int pipeline = 0;

  public static final class Seeking {
    public static final double kP = -0.6;
  }

  public static final class AimTarget {
    public static final double
      kP = 0.048,
      minCommand = 0.15;
  }

  public static final class RangeTarget {
    public static final double 
      kPDistance = -0.1;
  }
  
  public static final class AimAndRangeTarget {
    public static final double 
      kP = -0.04,
      kPDistance = 0.050,
      minCommand = 0.05;
  }
}