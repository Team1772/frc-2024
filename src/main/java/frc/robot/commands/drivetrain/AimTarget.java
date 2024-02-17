package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.components.Limelight;
import frc.core.components.Limelight.LedMode;
import frc.robot.constants.LimelightConstants;
import frc.robot.subsystems.Drivetrain;

public class AimTarget extends Command {
  private Drivetrain drivetrain;

  public AimTarget(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;

    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
    Limelight.setLed(LedMode.OFF);
    Limelight.setPipeline(LimelightConstants.pipeline);
  }

  @Override
  public void execute() {
    double apriltag = Limelight.getApriltag();
    if (apriltag == 4) {
      double x = Limelight.getX(),
          headingError = -(x),
          adjust = 0;

      if (x > 1) {
        adjust = LimelightConstants.AimTarget.kP *
            headingError -
            LimelightConstants.AimTarget.minCommand;
      } else if (x < 1) {
        adjust = LimelightConstants.AimTarget.kP *
            headingError +
            LimelightConstants.AimTarget.minCommand;
      }
      double rightSpeed = 0,
          leftSpeed = 0;
      rightSpeed -= adjust;
      leftSpeed += adjust;

      this.drivetrain.tankDrive(leftSpeed, rightSpeed);

      System.out.println("adjust: " + adjust);
      System.out.println("left speed: " + leftSpeed);
      System.out.println("right speed: " + rightSpeed);

    }
  }

  @Override
  public void end(boolean interrupted) {
    Limelight.setLed(LedMode.OFF);
  }
}