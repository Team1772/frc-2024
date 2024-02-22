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
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;

public class ReverseAndDownIntake extends ParallelCommandGroup{
      public ReverseAndDownIntake(Intake intake, IntakeMove intakeMove, TrajectoryBuilder trajectoryBuilder, String fileName, boolean updateOdometry) {
        super.addCommands(
                new IntakeDownToSensor(intakeMove),
                new CollectOn(intake),
                trajectoryBuilder.build(updateOdometry, fileName)
        );
    }
}
