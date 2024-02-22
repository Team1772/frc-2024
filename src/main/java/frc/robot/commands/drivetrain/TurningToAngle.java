package frc.robot.commands.drivetrain;

import frc.core.util.oi.SmartController;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

public class TurningToAngle extends Command {
  private Drivetrain drivetrain;
  private int angle;
  private static final int ANGLE_THRESHOLD = 8;
  private boolean isAngle;

  private double forwardSpeed;
  private double rotationSpeed;

  public TurningToAngle(Drivetrain drivetrain, int angle, double forwardSpeed, double rotationSpeed) {
    this.drivetrain = drivetrain;
    this.angle = angle;
    this.drivetrain.resetNavX();
    this.isAngle = false;
    this.forwardSpeed = forwardSpeed;
    this.rotationSpeed = rotationSpeed;
    addRequirements(this.drivetrain);
  }

  @Override
  public void execute() {
    if(Math.abs(this.angle - (int)this.drivetrain.getAngle()) < ANGLE_THRESHOLD){
      this.isAngle = true;
    }
    if(this.angle < 0){
      if(Math.abs(this.angle - (int)this.drivetrain.getAngle()) > ANGLE_THRESHOLD){
        drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
      }
    }else{
        drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
    }
  }

  @Override
  public boolean isFinished() {
    return isAngle;
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.tankDrive(0,0);
  }
}