package frc.robot.commands.autonomo;

import frc.core.util.TrajectoryBuilder;
import frc.robot.commands.intake.Release;
import frc.robot.commands.intake.ReleaseTimer;
import frc.robot.commands.shooterTest.ShootOff;
import frc.robot.commands.shooterTest.ShootOn;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterTest;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Auto1 extends SequentialCommandGroup {

    public Auto1(Drivetrain drivetrain, ShooterTest shooter, Intake intake, TrajectoryBuilder trajectoryBuilder) {
        super.addCommands(
                new ShootOn(shooter, 0.75),
                new WaitCommand(0.75),
                new ReleaseTimer(intake),
                new ShootOff(shooter),
                trajectoryBuilder.build(true, "1-forward"),
                new WaitCommand(0.6),
                trajectoryBuilder.build(false, "1-reverse"),
                new ShootOn(shooter, 0.75),
                new WaitCommand(0.75),
                new ReleaseTimer(intake),
                new ShootOff(shooter));
    }
}