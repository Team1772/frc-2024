package frc.robot.buttonBindings;

import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.core.util.oi.SmartController;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.Quasistasic;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;

public class DriverButtonBindings {

  private final Drivetrain drivetrain;
  private SmartController driver;

  public DriverButtonBindings(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    this.driver = new SmartController(ControllerConstants.kDriverControllerPort);

  }

  public void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
        new ArcadeDrive(
            this.drivetrain,
            () -> -driver.getLeftY(),
            () -> driver.getRightX(),
            driver));
  }

  public void buttonBindingsSysId() {
    if (DrivetrainConstants.SysId.isSysIdTunning) {
      this.driver.whileAButton(drivetrain.sysIdDynamic(Direction.kReverse));
      this.driver.whileYButton(drivetrain.sysIdDynamic(Direction.kForward));
      this.driver.whileBButton(drivetrain.sysIdQuasistatic(Direction.kForward));
      this.driver.whileXButton(drivetrain.sysIdQuasistatic(Direction.kReverse));
    }
  }

}
