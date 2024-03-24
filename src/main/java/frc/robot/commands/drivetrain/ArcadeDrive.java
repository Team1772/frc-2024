package frc.robot.commands.drivetrain;

import frc.core.components.Limelight;
import frc.core.components.Limelight.LedMode;
import frc.core.util.LedStrip;
import frc.core.util.oi.SmartController;
import frc.robot.RobotContainer;
import frc.robot.buttonBindings.OperatorButtonBindings;
import frc.robot.constants.LimelightConstants;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

public class ArcadeDrive extends Command {
  private Drivetrain drivetrain;
  private DoubleSupplier forward;
  private DoubleSupplier rotation;
  private SmartController driver;

  private static final double DRIVE_LIMITER = 0.5;
  private final double DRIVE_VELOCITY_LIMIT = 0.4;

  public ArcadeDrive(Drivetrain drivetrain, DoubleSupplier forward, DoubleSupplier rotation, SmartController driver) {
    this.drivetrain = drivetrain;
    this.forward = forward;
    this.rotation = rotation;
    this.driver = driver;

    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
    Limelight.setLed(LedMode.OFF);
    Limelight.setPipeline(LimelightConstants.pipeline);
  }

  @Override
  public void execute() {

    if (this.driver.getLeftBumper().getAsBoolean()) {
      this.drivetrain.arcadeDrive(this.forward.getAsDouble() * DRIVE_LIMITER, this.rotation.getAsDouble());
    } else {
      this.drivetrain.arcadeDrive(this.forward.getAsDouble(), this.rotation.getAsDouble());
    }

    if (Limelight.isTarget()) {
      double yErro = Math.abs(Limelight.getY());
      double xError = Math.abs(Limelight.getX());

      if (xError <= 11 && yErro <= 4 && RobotContainer.atSettedVelocity()) {
        driver.enableRumble();
        OperatorButtonBindings.operator.enableRumble();

        LedStrip.identifier = 3;
      } else {
        driver.disableRumble();
        OperatorButtonBindings.operator.disableRumble();
      }
    }
  }
}