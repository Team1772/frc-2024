package frc.core.util.pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.constants.PIDTalonConstants;
import frc.robot.constants.ShooterConstants;

public class TalonVelocity extends PIDTalon {

  private double velocityUnitsPer100ms;
  private static final int ERROR_LIMIT = 2000;

  public TalonVelocity(
      WPI_TalonSRX master,
      boolean isMasterInverted,
      boolean isFollowersInverted,
      boolean isSensorPhase,
      double nominalOutputForwardValue,
      double nominalOutputReverseValue,
      double peakOutputForwardValue,
      double peakOutputReverseValue,
      Gains gains,
      WPI_TalonSRX... followers) {
    super(
        master,
        isMasterInverted,
        isFollowersInverted,
        isSensorPhase,
        nominalOutputForwardValue,
        nominalOutputReverseValue,
        peakOutputForwardValue,
        peakOutputReverseValue,
        gains,
        followers);
  }

  public TalonVelocity(
      WPI_TalonSRX master,
      boolean isMasterInverted,
      boolean isFollowersInverted,
      boolean isSensorPhase,
      Gains gains,
      WPI_TalonSRX... followers) {
    this(
        master,
        isMasterInverted,
        isFollowersInverted,
        isSensorPhase,
        PIDTalonConstants.nominalOutputForwardValue,
        PIDTalonConstants.nominalOutputReverseValue,
        PIDTalonConstants.peakOutputForwardValue,
        PIDTalonConstants.peakOutputReverseValue,
        gains,
        followers);
  }

  public TalonVelocity(
      WPI_TalonSRX master,
      boolean isMasterInverted,
      boolean isSensorPhase,
      Gains gains,
      WPI_TalonSRX... followers) {
    this(
        master,
        isMasterInverted,
        false,
        isSensorPhase,
        PIDTalonConstants.nominalOutputForwardValue,
        PIDTalonConstants.nominalOutputReverseValue,
        PIDTalonConstants.peakOutputForwardValue,
        PIDTalonConstants.peakOutputReverseValue,
        gains,
        followers);
  }

  public TalonVelocity(
      WPI_TalonSRX master,
      Gains gains,
      WPI_TalonSRX... followers) {
    this(
        master,
        false,
        false,
        PIDTalonConstants.isSensorPhase,
        PIDTalonConstants.nominalOutputForwardValue,
        PIDTalonConstants.nominalOutputReverseValue,
        PIDTalonConstants.peakOutputForwardValue,
        PIDTalonConstants.peakOutputReverseValue,
        gains,
        followers);
  }

  private void setVelocity(double rpm, double dutyCycle) {
    dutyCycle = rpm > (ShooterConstants.maxRPM) ? dutyCycle : 1;
    this.velocityUnitsPer100ms = dutyCycle * rpm * 4096 / 600;

    super.master.set(ControlMode.Velocity, velocityUnitsPer100ms);
  }

  public void setPercentOutput(double speed){
    this.master.set(ControlMode.PercentOutput, speed);
  }
  public void setRPM(double rpm, double dutyCycle) {
    this.setVelocity(rpm, dutyCycle);
  }

  public void setVelocityMetersPerSecond(double velocityMetersPerSecond, double dutyCycle, double wheelRadius) {
    var rpm = (velocityMetersPerSecond * 60) / (2 * Math.PI * wheelRadius);

    this.setVelocity(rpm, dutyCycle);
  }

  public void stop() {
    //super.master.set(ControlMode.PercentOutput, 0);
    super.master.stopMotor();
  }

  public boolean atSettedVelocity() {
    if (this.getSelectedSensorVelocity() == 0)
      return false;

    return (Math.abs(this.velocityUnitsPer100ms - super.master.getSelectedSensorVelocity())) <= Math.abs(ERROR_LIMIT);
  }

  public double getSelectedSensorVelocity() {
    return super.master.getSelectedSensorVelocity();
  }
}