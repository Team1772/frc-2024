package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.subsystems.Limelight;
import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.SmartController;
import frc.robot.commands.LimelightCommand;
import frc.robot.commands.autonomo.Auto1;
import frc.robot.commands.autonomo.AutoTeste;
import frc.robot.commands.drivetrain.AimTarget;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;
  public SmartController driver;
  public SmartController operator;
  public TrajectoryBuilder trajectoryBuilder;
  public LimelightCommand limelightCommand;
  public Limelight limelightSubsystem;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();

    this.driver = new SmartController(ControllerConstants.kDriverControllerPort);
    this.operator = new SmartController(ControllerConstants.kOperatorControllerPort);
    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "1-forward", "1-reverse");
    configureButtonBindings();

  }

  private void configureButtonBindings() {
    // limelightSubsystem = new Limelight("");
    // limelightCommand = new LimelightCommand(limelightSubsystem);
    this.buttonBindingsDrivetain();
    this.buttonBindingsSysId();
  }

  private void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
        new ArcadeDrive(
            this.drivetrain,
            () -> -driver.getLeftY(),
            () -> driver.getRightX(),
            driver));
  }

  private void buttonBindingsSysId() {
    if (DrivetrainConstants.SysId.isSysIdTunning) {
      this.drivetrain.getSysIdTunning().enableSysIdTunning();
      this.driver.whileAButton(drivetrain.sysIdDynamic(Direction.kReverse));
      this.driver.whileYButton(drivetrain.sysIdDynamic(Direction.kForward));
      this.driver.whileBButton(drivetrain.sysIdQuasistatic(Direction.kForward));
      this.driver.whileXButton(drivetrain.sysIdQuasistatic(Direction.kReverse));
    }
  }

  public Command getAutonomousCommand() {
    return new Auto1(drivetrain, trajectoryBuilder); // new AutoLimelight(limelightSubsystem, drivetrain);
  }
}
