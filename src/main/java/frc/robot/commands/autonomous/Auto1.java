package frc.robot.commands.autonomous;

import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake.ReleaseTimer;
import frc.robot.commands.shooter.ShootAutonomous;
import frc.robot.commands.shooter.ShootOn;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Auto1 extends SequentialCommandGroup {

  public Auto1(Shooter shooter, Intake intake, TrajectoryBuilder trajectoryBuilder) {
    super.addCommands(
        new ShootOn(shooter, 0.3)
        //new ShootAutonomous(3.5, intake, shooter)
    );
  }
}