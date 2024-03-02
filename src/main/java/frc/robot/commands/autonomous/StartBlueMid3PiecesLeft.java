package frc.robot.commands.autonomous;

import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.autonomous.Mid2Pieces.ForwardAndUpIntake;
import frc.robot.commands.autonomous.Mid2Pieces.ReverseAndDownIntake;
import frc.robot.commands.intake.CollectOff;
import frc.robot.commands.intake.ReleaseTimer;
import frc.robot.commands.intake.ReleaseToShoot;
import frc.robot.commands.shooter.ShootAutonomous;
import frc.robot.commands.shooter.ShootOff;
import frc.robot.commands.shooter.ShootOn;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class StartBlueMid3PiecesLeft extends SequentialCommandGroup {
  // Max Acceleration = 1.1 Max Velocity = 1.1
  public StartBlueMid3PiecesLeft(Drivetrain drivetrain, Shooter shooter, Intake intake, IntakeMove intakeMove,
      TrajectoryBuilder trajectoryBuilder) {
    super.addCommands(
        // new ShootAutonomous(1.8, intake, shooter),

        new ShootOn(shooter, 0.85),
        new WaitCommand(1),
        new ReleaseTimer(intake),
        new ReverseAndDownIntake(intake, intakeMove, trajectoryBuilder, "1-reverse", true),
        new ForwardAndUpIntake(shooter, intake, intakeMove, trajectoryBuilder, "1-forward", false),
        new CollectOff(intake),
        new ReleaseToShoot(intake, shooter, 1),
        new ShootOff(shooter),
        new ReverseAndDownIntake(intake, intakeMove, trajectoryBuilder, "1-reverse2PieceLeft", false),
        new ForwardAndUpIntake(shooter, intake, intakeMove, trajectoryBuilder, "1-forward2PieceLeft", false),
        new CollectOff(intake),
        new ReleaseToShoot(intake, shooter, 1),
        new ShootOff(shooter));
  }
}