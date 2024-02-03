package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.robot.buttonBindings.DriverButtonBindings;
import frc.robot.buttonBindings.OperatorButtonBindings;
import frc.robot.commands.autonomo.Auto1;
import frc.robot.commands.autonomo.AutoTeste;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.OperatorRumble;
import frc.robot.subsystems.ShooterTest;

public class RobotContainer {
  private final Drivetrain drivetrain;
  private final ShooterTest shooterTest;
  public TrajectoryBuilder trajectoryBuilder;
  public DriverButtonBindings driver;
  public OperatorButtonBindings operator;
  public IntakeMove intakeMove;
  public Intake intake;
  public OperatorRumble operatorRumble;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();
    this.shooterTest = new ShooterTest();
    this.intakeMove = new IntakeMove();
    this.intake = new Intake();

    this.driver = new DriverButtonBindings(this.drivetrain, this.shooterTest);
    this.operator = new OperatorButtonBindings(this.drivetrain, this.intakeMove, this.intake, this.shooterTest, this.operatorRumble);

    configureButtonBindings();

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "1-forward", "1-reverse");
  }

  private void configureButtonBindings() {
    this.driver.buttonBindingsDrivetain();
    this.driver.buttonBindingsSysId();
    this.operator.buttonBindingsShooterTest();
    this.operator.buttonBindingsIntakeMove();
    this.operator.buttonBindingsIntake();
    this.operator.buttonBindingsOperatorRumble();
  }

  public Command getAutonomousCommand() {
    return new Auto1(drivetrain, shooterTest, intake, trajectoryBuilder);
  }
}
