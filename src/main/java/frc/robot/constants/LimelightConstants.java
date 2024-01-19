package frc.robot.constants;

public final class LimelightConstants {
  
  public static final String tableName = "limelight";
  public static final int aprilTagPipeline = 0;
  public static final int tapePipeline = 1;
  public static final int driverPipeline = 2;

  public static final class Seeking {
    public static final double kP = -0.1;
  }

  public static final class AimTarget {
    public static final double
      kP = 0.06,
      minCommand = 0.12;
  }

  public static final class RangeTarget {
    public static final double 
      kPDistance = -0.1;
  }
  
  public static final class AimAndRangeTarget {
    public static final double 
      kP = -0.1,
      kPDistance = -0.50,
      minCommand = 0.05;
  }
}