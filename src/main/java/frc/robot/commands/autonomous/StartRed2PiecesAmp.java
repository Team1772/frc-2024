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
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class StartRed2PiecesAmp extends SequentialCommandGroup {
  // Max Acceleration = 1.1 Max Velocity = 1.1
  public StartRed2PiecesAmp(Drivetrain drivetrain, Shooter shooter, Intake intake, IntakeMove intakeMove,
      TrajectoryBuilder trajectoryBuilder) {
    super.addCommands(
       // new ShootAutonomous(19, intake, shooter),
        new ShootTimer(shooter, 0.8, 0.8, 1),
        new WaitCommand(0.8),
        new ParallelCommandGroup(
         new ReleaseTimer(intake),
          new ShootTimer(shooter, 0.8, 0.8, 1.5)
        ),
        new ReverseAndDownIntake(intake, intakeMove, trajectoryBuilder, "2r-fowardCollectNote", true),
        //new ForwardAndUpIntake(shooter, intake, intakeMove, trajectoryBuilder, "2r-fowardShootAmp", false),
        new ParallelCommandGroup(
          new IntakeUpToSensor(intakeMove),
          trajectoryBuilder.build(false, "2r-fowardShootAmp")
        ),
        new CollectOff(intake),
        new ShootTimer(shooter, 0.23, 0.23, 1),
        //new WaitCommand(0.8),
        new ShootAutonomous(3.5, intake, shooter)

        //new ReleaseToShoot(intake, shooter, 1),
        // new ShootOff(shooter)
);
  }
}