package frc.robot.commands.autonomous;

import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.intake.SmartCollectTimer;
import frc.robot.commands.intake_move.IntakeUpToSensor;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Auto2 extends SequentialCommandGroup {

    public Auto2(Drivetrain drivetrain, IntakeMove intakeMove, Intake intake, TrajectoryBuilder trajectoryBuilder) {
        super.addCommands(
                 //new SmartShooterTimer(5, 2.5, intake, shooter),
                 new SmartCollectTimer(10, intake, intakeMove),
                 new IntakeUpToSensor(intakeMove)
        );
    }
}