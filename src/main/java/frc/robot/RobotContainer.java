package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.TrajectoryBuilder;
import frc.robot.buttonBindings.DriverButtonBindings;
import frc.robot.buttonBindings.OperatorButtonBindings;
import frc.robot.commands.autonomous.Auto1;
import frc.robot.commands.autonomous.AutoTeste;
import frc.robot.commands.autonomous.Mid2PiecesAuto;
import frc.robot.commands.autonomous.StartBlueAtLeft2PiecesLeftNote;
import frc.robot.commands.autonomous.StartBlueMid3PiecesRight;
import frc.robot.commands.autonomous.StartRed2PiecesAmp;
import frc.robot.subsystems.Climber;
//import frc.robot.subsystems.DriveNeoTest;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  private final Drivetrain drivetrain;
  public TrajectoryBuilder trajectoryBuilder;
  public DriverButtonBindings driver;
  public OperatorButtonBindings operator;
  public IntakeMove intakeMove;
  public Intake intake;
  public Climber climber;
  private Shooter shooter;
//  private DriveNeoTest driveNeoTest;

  public RobotContainer() {
    this.drivetrain = new Drivetrain();
    this.intakeMove = new IntakeMove();
    this.intake = new Intake();
    this.climber = new Climber();
    this.shooter = new Shooter();
//    this.driveNeoTest = new DriveNeoTest();

    this.driver = new DriverButtonBindings(this.drivetrain);
    this.operator = new OperatorButtonBindings(this.drivetrain, this.intakeMove, this.intake, this.climber, this.shooter);

    configureButtonBindings();

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain, "1-forward", "1-reverse", "1-reverse2Piece", "1-forward2Piece", "2r-fowardCollectNote", "2r-fowardShootAmp", "2r-fowardCollectNoteReverse", "2b-forward2PieceLeft", "2b-reverse2PieceLeft", "1-forward2PieceRight", "1-reverse2PieceRight");
  }

  private void configureButtonBindings() {
    this.driver.buttonBindingsDrivetain();
    //this.driver.buttonBindingsSysId();
    //this.driver.buttonBindingsLimelight();
    this.operator.buttonBindingsShooterTest();
    this.operator.buttonBindingsIntakeMove();
    this.operator.buttonBindingsIntake();
    this.operator.buttonBindingsClimber();
    //this.operator.buttonBindingsOperatorRumble();
  }

  public Command getAutonomousCommand() {
    return new StartBlueMid3PiecesRight(drivetrain, shooter, intake, intakeMove, trajectoryBuilder);
  }
}
