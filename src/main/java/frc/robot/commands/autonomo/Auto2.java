package frc.robot.commands.autonomo;

import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake.ReleaseTimer;
import frc.robot.commands.intake.SmartCollectTimer;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.commands.shooterTest.ShootOff;
import frc.robot.commands.shooterTest.ShootOn;
import frc.robot.commands.shooterTest.SmartShooterTimer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.ShooterTest;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Auto2 extends SequentialCommandGroup {

    public Auto2(Drivetrain drivetrain, ShooterTest shooter, IntakeMove intakeMove, Intake intake, TrajectoryBuilder trajectoryBuilder) {
        super.addCommands(
                 new SmartShooterTimer(5, 2.5, intake, shooter),
                 new SmartCollectTimer(10, intake, intakeMove),
                 new IntakeUpToSensor(intakeMove)
        );
    }
}