package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;
import frc.robot.commands.climber.ChangeClimberSize;
import frc.robot.commands.intake.IntakeCollect;
import frc.robot.commands.intake.IntakeRelease;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpDown;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooterTest.ShooterAndIntakeCollect;
import frc.robot.commands.shooterTest.Shoot;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.ShooterTest;

public class OperatorButtonBindings {
  public SmartController operator = new SmartController(ControllerConstants.kOperatorControllerPort);
  public Drivetrain drivetrain;
  public Intake intake;
  public IntakeMove intakeMove;
  public ShooterTest shooterTest;
  public Climber climber;

  public OperatorButtonBindings(Drivetrain drivetrain, IntakeMove intakeMove, Intake intake, ShooterTest shooterTest,
      Climber climber) {
    this.drivetrain = drivetrain;
    this.intakeMove = intakeMove;
    this.shooterTest = shooterTest;
    this.intake = intake;
    this.climber = climber;
  }

  public void buttonBindingsIntakeMove() {
    this.operator.whileXUp(new IntakeUpToSensor(this.intakeMove));
    this.operator.whileXDown(new IntakeDownToSensor(this.intakeMove));
    new IntakeUpDown(this.intakeMove, () -> this.operator.getLeftY());
  }

  public void buttonBindingsShooterTest() {
    // Speaker
    this.operator.whileRightBumper(new Shoot(shooterTest, 0.75, 0.7));
    // Amp
    this.operator.whileLeftBumper(new Shoot(shooterTest, 0.3, 0.15));
    // Trap
    this.operator.whileBButton(new Shoot(shooterTest, 0.15, 0.4));
  }

  public void buttonBindingsIntake() {
    this.operator.whileAButton(new IntakeCollect(intake, DriverButtonBindings.driver, operator));
    this.operator.whileYButton(new IntakeRelease(intake));
    this.operator.whileXButton(new ShooterAndIntakeCollect(shooterTest, intake, intakeMove));
  }

  public void buttonBindingsClimber() {
    this.operator.whileXRight(
        new ChangeClimberSize(
            () -> this.operator.getRightY(),
            this.climber));
  }
}