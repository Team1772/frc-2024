package frc.robot.buttonBindings;

import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.core.util.oi.SmartController;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;

public class DriverButtonBindings {

  private final Drivetrain drivetrain;
  public static SmartController driver;

  public DriverButtonBindings(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    DriverButtonBindings.driver = new SmartController(ControllerConstants.kDriverControllerPort);

    this.buttonBindingsDrivetain();
    this.buttonBindingsSysId();
  }

  public void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
        new ArcadeDrive(
            this.drivetrain,
            () -> driver.getLeftY(),
            () -> driver.getRightX(),
            driver));
  }

  public void buttonBindingsSysId() {
    if (DrivetrainConstants.SysId.isSysIdTunning) {
      DriverButtonBindings.driver.whileAButton(drivetrain.sysIdDynamic(Direction.kReverse));
      DriverButtonBindings.driver.whileYButton(drivetrain.sysIdDynamic(Direction.kForward));
      DriverButtonBindings.driver.whileBButton(drivetrain.sysIdQuasistatic(Direction.kForward));
      DriverButtonBindings.driver.whileXButton(drivetrain.sysIdQuasistatic(Direction.kReverse));
    }
  }

  public void buttonBindingsLimelight() {
    // driver.whileXLeft(new AimAndRangeTarget(drivetrain));
    // driver.whileXRight(new Seeking(drivetrain));
  }

  public void shootLimelight() {
  }

}