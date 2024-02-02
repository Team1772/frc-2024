package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooterTest.Shoot;
import frc.robot.commands.shooterTest.ShootAndIntakeSpeaker;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.ShooterTest;

public class OperatorButtonBindings {
  private SmartController operator = new SmartController(ControllerConstants.kOperatorControllerPort);
  public Drivetrain drivetrain;
  public Intake Intake;
  public IntakeMove intakeMove;
  public ShooterTest shooterTest;

  public OperatorButtonBindings(Drivetrain drivetrain, IntakeMove intakeMove, Intake Intake, ShooterTest shooterTest) {
    this.drivetrain = drivetrain;
    this.intakeMove = intakeMove;
    this.shooterTest = shooterTest;

  }

  public void buttonBindingsIntakeMove() {
    this.operator.whileYButton(new IntakeUpToSensor(this.intakeMove));
    this.operator.whileAButton(new IntakeDownToSensor(this.intakeMove));
  }

  public void buttonBindingsShooterTest() {
    this.operator.whileRightBumper(new Shoot(shooterTest, 0.8));
    this.operator.whileLeftBumper(new Shoot(shooterTest, 0.27, 0.15));
    //this.operator.whileXUp(new ShootAndIntakeSpeaker(shooterTest, Intake, intakeMove));

  }

  public void buttonBindingsIntake() {
    this.operator.whileXDown(new Release(Intake));
  }
}