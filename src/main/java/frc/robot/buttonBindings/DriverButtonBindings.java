package frc.robot.buttonBindings;

import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.core.util.oi.SmartController;
//import frc.robot.commands.driveNeo.ArcadeDriveNeo;
import frc.robot.commands.drivetrain.AimTarget;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.RangeTarget;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.DrivetrainConstants;
//import frc.robot.subsystems.DriveNeoTest;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ShooterTest;

public class DriverButtonBindings {

  private final Drivetrain drivetrain;
  public static SmartController driver;
  // private DriveNeoTest driveNeoTest;

  public DriverButtonBindings(Drivetrain drivetrain, ShooterTest shooterTest) {
    this.drivetrain = drivetrain;
//    this.driveNeoTest = driveNeoTest;
    this.driver = new SmartController(ControllerConstants.kDriverControllerPort);

    this.buttonBindingsDrivetain();
    this.buttonBindingsSysId();
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

    public void buttonBindingsLimelight() {
      driver.whileXUp(new AimTarget(drivetrain));
      driver.whileXDown(new RangeTarget(drivetrain));
  }

}
