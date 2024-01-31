package frc.robot.constants;

public final class PIDTalonConstants {
  public static final boolean isSensorPhase = true;

  public static final int kPIDLoopIdx = 0,
      kTimeoutMs = 30,
      nominalOutputForwardValue = 0,
      nominalOutputReverseValue = 0,
      peakOutputForwardValue = 1,
      peakOutputReverseValue = -1;
}
