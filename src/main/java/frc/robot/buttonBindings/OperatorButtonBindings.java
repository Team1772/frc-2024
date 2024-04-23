package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;
import frc.robot.commands.climber.ChangeClimberSize;
import frc.robot.commands.intake.Collect;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooter.CollectHuman;
import frc.robot.commands.shooter.Shoot;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;

public class OperatorButtonBindings {
  public static SmartController operator = new SmartController(ControllerConstants.kOperatorControllerPort);
  public Drivetrain drivetrain;
  public Intake intake;
  public IntakeMove intakeMove;
  public Climber climber;
  public Shooter shooter;

  public OperatorButtonBindings(Drivetrain drivetrain, IntakeMove intakeMove, Intake intake,
      Climber climber, Shooter shooter) {
    this.drivetrain = drivetrain;
    this.intakeMove = intakeMove;
    this.intake = intake;
    this.climber = climber;
    this.shooter = shooter;
  }

  public void buttonBindingsIntakeMove() {
    OperatorButtonBindings.operator.whileXUp(new IntakeUpToSensor(this.intakeMove));
    OperatorButtonBindings.operator.whileXDown(new IntakeDownToSensor(this.intakeMove));
  }

  public void buttonBindingsShooterTest() {
    // Speaker
    OperatorButtonBindings.operator.whileRightBumper(new Shoot(shooter, 18));

    // Amp
    OperatorButtonBindings.operator.whileLeftBumper(new Shoot(shooter, 3.1));
  }

  public void buttonBindingsIntake() {
    OperatorButtonBindings.operator.whileAButton(new Collect(intake, DriverButtonBindings.driver, operator));
    OperatorButtonBindings.operator.whileYButton(new Release(intake));
    OperatorButtonBindings.operator.whileXButton(new CollectHuman(shooter, intake, intakeMove));
    OperatorButtonBindings.operator.whileBButton(new Release(intake, 0.7));
  }

  public void buttonBindingsClimber() {
    OperatorButtonBindings.operator.whileXRight(
        new ChangeClimberSize(
            () -> -OperatorButtonBindings.operator.getRightY(),
            this.climber));
  }
}