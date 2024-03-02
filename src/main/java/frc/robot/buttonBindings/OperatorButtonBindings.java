package frc.robot.buttonBindings;

import frc.core.util.Led;
import frc.core.util.oi.SmartController;
import frc.robot.commands.climber.ChangeClimberSize;
import frc.robot.commands.intake.Collect;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpDown;
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
    // this.intakeMove.setDefaultCommand(
    // new IntakeUpDown(intakeMove, () -> this.operator.getLeftY())
    // );
    this.operator.whileXUp(new IntakeUpToSensor(this.intakeMove));
    this.operator.whileXDown(new IntakeDownToSensor(this.intakeMove));
    // new IntakeUpDown(this.intakeMove, () -> this.operator.getLeftY());
  }

  public void buttonBindingsShooterTest() {
    // Speaker
    // this.operator.whileRightBumper(new Shoott(shooter));

    this.operator.whileRightBumper(new Shoot(shooter, 18));
    //this.operator.whileRightBumper(new Shoot(shooter, 0.75));

    // this.operator.whileRightBumper(new PrepareShoot(16, shooter));
    // Amp
    this.operator.whileLeftBumper(new Shoot(shooter, 3.1));
    //this.operator.whileLeftBumper(new Shoot(shooter, 0.2));

  }

  public void buttonBindingsIntake() {
    this.operator.whileAButton(new Collect(intake, DriverButtonBindings.driver, operator));
    this.operator.whileYButton(new Release(intake));
    this.operator.whileXButton(new CollectHuman(shooter, intake, intakeMove));
    this.operator.whileBButton(new Release(intake, 0.7));
  }

  public void buttonBindingsClimber() {
    this.operator.whileXRight(
        new ChangeClimberSize(
            () -> -this.operator.getRightY(),
            this.climber));
  }
}