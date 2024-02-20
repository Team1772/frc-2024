package frc.robot.commands.autonomous;

import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.intake.CollectOff;
import frc.robot.commands.intake.CollectOn;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.commands.intake_move.IntakeUpDown;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooter.ShootAutonomous;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Mid2PiecesAuto extends SequentialCommandGroup {

    public Mid2PiecesAuto(Drivetrain drivetrain, Shooter shooter, Intake intake, IntakeMove intakeMove, TrajectoryBuilder trajectoryBuilder) {
        super.addCommands(
                new ShootAutonomous(2, intake, shooter),
                new WaitCommand(0.4),
                new IntakeDownToSensor(intakeMove),
                new CollectOn(intake),
                trajectoryBuilder.build(true, "1-forward"),
                new WaitCommand(0.4),
                new CollectOff(intake),
                new IntakeUpToSensor(intakeMove),
                trajectoryBuilder.build(false, "1-reverse"),
                new ShootAutonomous(2, intake, shooter)
        );
    }
}