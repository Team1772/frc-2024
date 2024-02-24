package frc.robot.commands.autonomous.Mid2Pieces;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.intake.CollectOff;
import frc.robot.commands.intake.CollectOn;
import frc.robot.commands.intake.ReleaseToShoot;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooter.ShootAutonomous;
import frc.robot.commands.shooter.ShootOff;
import frc.robot.commands.shooter.ShootOn;
import frc.robot.commands.shooter.ShootTimer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;

public class ForwardAndUpIntake extends ParallelCommandGroup {
  public ForwardAndUpIntake(Shooter shooter, Intake intake, IntakeMove intakeMove, TrajectoryBuilder trajectoryBuilder,
      String fileName, boolean updateOdometry) {
    super.addCommands(
        new IntakeUpToSensor(intakeMove),
        new ShootOn(shooter, 0.9),
        trajectoryBuilder.build(updateOdometry, fileName));
  }
}
