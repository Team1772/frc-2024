package frc.robot.commands.autonomous;

import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.autonomous.Mid2Pieces.ForwardAndUpIntake;
import frc.robot.commands.autonomous.Mid2Pieces.ReverseAndDownIntake;
import frc.robot.commands.intake.CollectOff;
import frc.robot.commands.intake.ReleaseTimer;
import frc.robot.commands.intake.ReleaseToShoot;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooter.ShootAutonomous;
import frc.robot.commands.shooter.ShootOff;
import frc.robot.commands.shooter.ShootOn;
import frc.robot.commands.shooter.ShootTimer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class StartBlueAtLeft2PiecesLeftNote extends SequentialCommandGroup {
  // Max Acceleration = 1.1 Max Velocity = 1.1
  public StartBlueAtLeft2PiecesLeftNote(Drivetrain drivetrain, Shooter shooter, Intake intake,
      IntakeMove intakeMove,
      TrajectoryBuilder trajectoryBuilder) {
    super.addCommands(
        new ShootTimer(shooter, 0.85, 0.85, 1),
        new ParallelCommandGroup(
            new ReleaseTimer(intake),
            new ShootTimer(shooter, 0.85, 0.85, 1.5)),
        // new ShootAutonomous(19, intake, shooter),
        new ReverseAndDownIntake(intake, intakeMove, trajectoryBuilder, "2b-forward2PieceLeft", true),
        new ForwardAndUpIntake(shooter, intake, intakeMove, trajectoryBuilder, "2b-reverse2PieceLeft", false),
        new ReleaseTimer(intake),
        new ShootOff(shooter),
        trajectoryBuilder.build(false, "2b-forwardToMid")

    );
  }
}