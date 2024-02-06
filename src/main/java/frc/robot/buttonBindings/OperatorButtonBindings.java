package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;
import frc.robot.commands.intake.Collect;
import frc.robot.commands.intake.IntakeManual;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake.ShootAmp;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpDown;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooterTest.CollectHuman;
import frc.robot.commands.shooterTest.Shoot;
import frc.robot.commands.shooterTest.ShootAndIntakeSpeaker;
import frc.robot.commands.shooterTest.ShootManual;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.ShooterTest;
import frc.robot.subsystems.OperatorRumble;

public class OperatorButtonBindings {
  private SmartController operator = new SmartController(ControllerConstants.kOperatorControllerPort);
  public Drivetrain drivetrain;
  public Intake intake;
  public IntakeMove intakeMove;
  public ShooterTest shooterTest;
  public OperatorRumble operatorRumble;

  public OperatorButtonBindings(Drivetrain drivetrain, IntakeMove intakeMove, Intake intake, ShooterTest shooterTest, OperatorRumble operatorRumble) {
    this.drivetrain = drivetrain;
    this.intakeMove = intakeMove;
    this.shooterTest = shooterTest;
    this.intake = intake;
    this.operatorRumble = operatorRumble;
  }

  public void buttonBindingsIntakeMove() {
    this.operator.whileXUp(new IntakeUpToSensor(this.intakeMove));
    this.operator.whileXDown(new IntakeDownToSensor(this.intakeMove));
    new IntakeUpDown(this.intakeMove, () -> this.operator.getLeftY());
  }

  public void buttonBindingsShooterTest() {
    this.operator.whileRightBumper(new Shoot(shooterTest, 0.75, 0.7));
    this.operator.whileLeftBumper(new Shoot(shooterTest, 0.3, 0.1));
    // this.operator.whileXUp(new ShootAndIntakeSpeaker(shooterTest, Intake,
    // intakeMove));

  }

  public void buttonBindingsIntake() {
    this.operator.whileAButton(new Collect(intake));
    this.operator.whileYButton(new Release(intake));
    this.operator.whileXButton(new CollectHuman(shooterTest, intake));
    this.operator.whileBButton(new ShootAmp(intake));
  }

  public void buttonBindingsOperatorRumble() {
    new OperatorRumble();
  }
}