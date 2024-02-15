package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.util.pid.Gains;
import frc.core.util.pid.TalonVelocity;
import frc.robot.constants.ShooterConstants;

public class Shooter extends SubsystemBase {

  private static final int NEUTRAL_SENSOR_VELOCITY = Math.abs(200);
  private static final int SAFE_REVERSE_SENSOR_VELOCITY = -1200;
  private final WPI_TalonSRX motorUpper;
  private final TalonVelocity shooterPIDUpper;
  private final WPI_TalonSRX motorLower;
  private final TalonVelocity shooterPIDLower;

  public Shooter() {
    this.motorUpper = new WPI_TalonSRX(ShooterConstants.motorUpperPort);
    this.motorLower = new WPI_TalonSRX(ShooterConstants.motorLowerPort);
    this.motorUpper.setNeutralMode(NeutralMode.Coast);
    this.motorLower.setNeutralMode(NeutralMode.Coast);

    this.shooterPIDUpper = new TalonVelocity(
        this.motorUpper,
        ShooterConstants.isMotorUpperInverted,
        ShooterConstants.isSensorPhase,
        new Gains(
            ShooterConstants.PID.kPVelocity,
            ShooterConstants.PID.kIVelocity,
            ShooterConstants.PID.kDVelocity,
            ShooterConstants.PID.kFVelocity,
            ShooterConstants.PID.kIZoneVelocity,
            ShooterConstants.PID.kPeakOutputVelocity));

    this.shooterPIDLower = new TalonVelocity(
        this.motorLower,
        ShooterConstants.isMotorLowerInverted,
        ShooterConstants.isSensorPhase,
        new Gains(
            ShooterConstants.PID.kPVelocity,
            ShooterConstants.PID.kIVelocity,
            ShooterConstants.PID.kDVelocity,
            ShooterConstants.PID.kFVelocity,
            ShooterConstants.PID.kIZoneVelocity,
            ShooterConstants.PID.kPeakOutputVelocity));

  }

  public void set(double speedUpper, double speedLower) {
    this.motorUpper.set(ControlMode.PercentOutput, speedUpper);
    this.motorLower.set(ControlMode.PercentOutput, speedLower);
  }

  public void setRPM(double rpm) {
    this.shooterPIDUpper.setRPM(
        rpm,
        ShooterConstants.PID.dutyCycle);

    this.shooterPIDLower.setRPM(
        rpm,
        ShooterConstants.PID.dutyCycle);
  }

  public void setVelocityMetersPerSecond(double velocityMetersPerSecond) {
    this.shooterPIDUpper.setVelocityMetersPerSecond(
        velocityMetersPerSecond,
        ShooterConstants.PID.dutyCycle,
        ShooterConstants.wheelRadiusMeters);

    this.shooterPIDLower.setVelocityMetersPerSecond(
        velocityMetersPerSecond,
        ShooterConstants.PID.dutyCycle,
        ShooterConstants.wheelRadiusMeters);
  }

  public boolean atSettedVelocity() {
     return this.shooterPIDUpper.atSettedVelocity() && this.shooterPIDLower.atSettedVelocity();
  }

  public boolean isSafetyShoot() {
    return this.shooterPIDUpper.getSelectedSensorVelocity() >= SAFE_REVERSE_SENSOR_VELOCITY && 
    this.shooterPIDLower.getSelectedSensorVelocity() >= SAFE_REVERSE_SENSOR_VELOCITY;
  }
  

  public boolean isShooterMoving() {
    return Math.abs(this.shooterPIDUpper.getSelectedSensorVelocity()) > NEUTRAL_SENSOR_VELOCITY &&
    Math.abs(this.shooterPIDLower.getSelectedSensorVelocity()) > NEUTRAL_SENSOR_VELOCITY;
  }

  // 210 > 200
  // 250
  //

  public void stop() {
    this.set(0, 0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("error", this.shooterPIDUpper.getClosedLoopError());
    SmartDashboard.putNumber("error", this.shooterPIDLower.getClosedLoopError());
    SmartDashboard.putNumber("selected sensor velocity", this.shooterPIDUpper.getSelectedSensorVelocity());
    SmartDashboard.putNumber("selected sensor velocity", this.shooterPIDLower.getSelectedSensorVelocity());
    SmartDashboard.putBoolean("isSettedVelocity", this.atSettedVelocity());
  }
}