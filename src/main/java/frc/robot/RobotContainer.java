package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.robot.buttonBindings.DriverButtonBindings;
import frc.robot.buttonBindings.OperatorButtonBindings;
import frc.robot.commands.autonomo.Auto1;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain;
  public TrajectoryBuilder trajectoryBuilder;
  public DriverButtonBindings driver;
  public OperatorButtonBindings operator;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();

    this.driver = new DriverButtonBindings(this.drivetrain);
    this.operator = new OperatorButtonBindings(this.drivetrain);

    configureButtonBindings();

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "1-forward", "1-reverse");
  }

  private void configureButtonBindings() {
    this.driver.buttonBindingsDrivetain();
    this.driver.buttonBindingsSysId();
  }

  public Command getAutonomousCommand() {
    return new Auto1(drivetrain, trajectoryBuilder);
  }
}
