package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;
import frc.robot.commands.climber.ChangeClimberSize;
import frc.robot.commands.intake.Collect;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpDown;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooter.PrepareShoot;
import frc.robot.commands.shooter.PrepareShootAmp;
import frc.robot.commands.shooterTest.CollectHuman;
import frc.robot.commands.shooterTest.Shoot;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterTest;

public class OperatorButtonBindings {
  public SmartController operator = new SmartController(ControllerConstants.kOperatorControllerPort);
  public Drivetrain drivetrain;
  public Intake intake;
  public IntakeMove intakeMove;
  public ShooterTest shooterTest;
  public Climber climber;
  public Shooter shooter;

  public OperatorButtonBindings(Drivetrain drivetrain, IntakeMove intakeMove, Intake intake, ShooterTest shooterTest,
      Climber climber, Shooter shooter) {
    this.drivetrain = drivetrain;
    this.intakeMove = intakeMove;
    this.shooterTest = shooterTest;
    this.intake = intake;
    this.climber = climber;
    this.shooter = shooter;
  }

  public void buttonBindingsIntakeMove() {
    this.operator.whileXUp(new IntakeUpToSensor(this.intakeMove));
    this.operator.whileXDown(new IntakeDownToSensor(this.intakeMove));
    new IntakeUpDown(this.intakeMove, () -> this.operator.getLeftY());
  }

  public void buttonBindingsShooterTest() {
    // Speaker
    // this.operator.whileRightBumper(new Shoott(shooter));

    // this.operator.whileRightBumper(new Shoot(shooterTest, 0.75, 0.7));
    this.operator.whileRightBumper(new PrepareShoot(18, shooter));
    // Amp
    this.operator.whileLeftBumper(new PrepareShootAmp(10, shooter));

    //this.operator.whileLeftBumper(new Shoot(shooterTest, 0.3, 0.15));
    // Trap
    this.operator.whileBButton(new Shoot(shooterTest, 0.15, 0.4));
  }

  public void buttonBindingsIntake() {
    this.operator.whileAButton(new Collect(intake, DriverButtonBindings.driver, operator));
    this.operator.whileYButton(new Release(intake));
    this.operator.whileXButton(new CollectHuman(shooterTest, intake, intakeMove));
  }

  public void buttonBindingsClimber() {
    this.operator.whileXRight(
        new ChangeClimberSize(
            () -> this.operator.getRightY(),
            this.climber));
  }
}