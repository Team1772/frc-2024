package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.LedStrip;
import frc.core.util.TrajectoryBuilder;
import frc.robot.buttonBindings.DriverButtonBindings;
import frc.robot.buttonBindings.OperatorButtonBindings;
import frc.robot.commands.autonomous.Auto1;
import frc.robot.commands.autonomous.AutoTeste;
import frc.robot.commands.autonomous.Blue2PiecesAmp;
import frc.robot.commands.autonomous.Mid2PiecesAuto;
import frc.robot.commands.autonomous.StartBlueAtLeft2PiecesLeftNote;
import frc.robot.commands.autonomous.StartBlueMid3PiecesLeft;
import frc.robot.commands.autonomous.StartREDAtRight2PiecesRightNote;
import frc.robot.commands.autonomous.StartBlueMid3PiecesRight;
import frc.robot.commands.autonomous.StartRed2PiecesAmp;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  private final Drivetrain drivetrain;
  public TrajectoryBuilder trajectoryBuilder;
  public static DriverButtonBindings driver;
  public static OperatorButtonBindings operator;
  public static IntakeMove intakeMove;
  public static Intake intake;
  public static Climber climber;
  public static Shooter shooter;
  public LedStrip led;

  public RobotContainer() {
    this.led = new LedStrip();
    this.drivetrain = new Drivetrain();
    this.intakeMove = new IntakeMove();
    this.intake = new Intake();
    this.climber = new Climber();
    this.shooter = new Shooter();
    this.driver = new DriverButtonBindings(this.drivetrain);
    this.operator = new OperatorButtonBindings(this.drivetrain, this.intakeMove, this.intake, this.climber,
        this.shooter);

    configureButtonBindings();
    // ledStrip.rainbow();

    this.trajectoryBuilder = new TrajectoryBuilder(drivetrain,
        "1-forward",
        "1-reverse",
        "2r-fowardCollectNote",
        "2r-fowardShootAmp",
        "2r-fowardCollectNoteReverse",
        "2b-forward2PieceLeft",
        "2b-reverse2PieceLeft",
        "2b-forwardToMid",
        "1-forward2PieceRight",
        "1-reverse2PieceRight",
        "2b-fowardShootAmp",
        "2r-fowardToMid"
        );
  }

  private void configureButtonBindings() {
    this.driver.buttonBindingsDrivetain();
    // this.driver.buttonBindingsSysId();
    this.operator.buttonBindingsShooterTest();
    this.operator.buttonBindingsIntakeMove();
    this.operator.buttonBindingsIntake();
    this.operator.buttonBindingsClimber();
    this.driver.buttonBindingsLimelight();
  }

  public LedStrip led() {
    return this.led;
  }

  public boolean isInfraredIntake() {
    return this.intake.isInfraredSensor();
  }

  public boolean isLimitMaxIntake() {
    return this.intakeMove.isLimitMax();
  }

  public boolean isLimitMinIntake() {
    return this.intakeMove.isLimitMin();
  }

  public boolean isLimitMinClimber(){
    return this.climber.isLimit();
  }

  public static boolean atSettedVelocity(){
    return shooter.atSettedVelocity();
  }
  public Command getAutonomousCommand() {
    return new StartBlueMid3PiecesRight(drivetrain, shooter, intake, intakeMove, trajectoryBuilder);
    // return new Auto1(drivetrain, trajectoryBuilder);
    // return new Blue2PiecesAmp(drivetrain, shooter, intake, intakeMove,
    // trajectoryBuilder);
    //return new StartRed2PiecesAmp(drivetrain, shooter, intake, intakeMove,
    //trajectoryBuilder);
    //return new StartREDAtRight2PiecesRightNote(drivetrain, shooter, intake, intakeMove, trajectoryBuilder);
    //return new StartBlueAtLeft2PiecesLeftNote(drivetrain, shooter, intake,
    //intakeMove, trajectoryBuilder);
    //return new Auto1(shooter, intake, trajectoryBuilder);
    //return new StartRed2PiecesAmp(drivetrain, shooter, intake, intakeMove, trajectoryBuilder);
  }
}
