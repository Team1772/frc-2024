package frc.robot.subsystems;

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
  private final TalonVelocity shooterPID;
  private final WPI_TalonSRX motorLower;

  public Shooter() {
    this.motorUpper = new WPI_TalonSRX(ShooterConstants.motorUpperPort);
    this.motorLower = new WPI_TalonSRX(ShooterConstants.motorLowerPort);
    this.motorUpper.setNeutralMode(NeutralMode.Coast);
    this.motorLower.setNeutralMode(NeutralMode.Coast);

    this.shooterPID = new TalonVelocity(
        this.motorUpper,
        ShooterConstants.isMotorUpperInverted,
        ShooterConstants.isMotorLowerInverted,
        ShooterConstants.isSensorPhase,
        new Gains(
            ShooterConstants.PID.kPVelocity,
            ShooterConstants.PID.kIVelocity,
            ShooterConstants.PID.kDVelocity,
            ShooterConstants.PID.kFVelocity,
            ShooterConstants.PID.kIZoneVelocity,
            ShooterConstants.PID.kPeakOutputVelocity),
        this.motorLower);
  }

  public void setRPM(double rpm) {
    this.shooterPID.setRPM(
        rpm,
        ShooterConstants.PID.dutyCycle);
  }

  public void setVelocityMetersPerSecond(double velocityMetersPerSecond) {
    this.shooterPID.setVelocityMetersPerSecond(
        velocityMetersPerSecond,
        ShooterConstants.PID.dutyCycle,
        ShooterConstants.wheelRadiusMeters);
  }

  public boolean atSettedVelocity() {
    return this.shooterPID.atSettedVelocity();
  }

  public boolean isSafetyShoot() {
    return this.shooterPID.getSelectedSensorVelocity() >= SAFE_REVERSE_SENSOR_VELOCITY;
  }

  public boolean isShooterMoving() {
    return Math.abs(this.shooterPID.getSelectedSensorVelocity()) > NEUTRAL_SENSOR_VELOCITY;
  }


  // 210 > 200
  // 250
  //

  public void stop() {
    this.shooterPID.stop();
  }

  public void stopPID() {
    this.shooterPID.stop();
  }

  public void setPercentOutput(double speed) {
    this.shooterPID.setPercentOutput(speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("error", this.shooterPID.getClosedLoopError());
    SmartDashboard.putNumber("selected sensor velocity", this.shooterPID.getSelectedSensorVelocity());
    SmartDashboard.putBoolean("isSettedVelocity", this.atSettedVelocity());
  }
}