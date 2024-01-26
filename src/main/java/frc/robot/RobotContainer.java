package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.SmartController;
import frc.robot.commands.autonomo.AutoTeste;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;
  public SmartController driver;
  public SmartController operator;
  public TrajectoryBuilder trajectoryBuilder;
  public RobotContainer() {
    this.drivetrain = new Drivetrain();

    this.driver = new SmartController(ControllerConstants.kDriverControllerPort);
    this.operator = new SmartController(ControllerConstants.kOperatorControllerPort);
    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain,"1","2");
    configureButtonBindings();

  }

  private void configureButtonBindings() {
    this.buttonBindingsDrivetain();
  }

  private void buttonBindingsDrivetain() {
    this.drivetrain.setDefaultCommand(
        new ArcadeDrive(
            this.drivetrain,
            () -> -driver.getLeftY(),
            () -> driver.getRightX(),
            driver));
  }

  public Command getAutonomousCommand() {
    return new AutoTeste(drivetrain, trajectoryBuilder);
  }
}
