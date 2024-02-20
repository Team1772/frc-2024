package frc.robot.commands.autonomous;

import frc.core.util.TrajectoryBuilder;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoTeste extends SequentialCommandGroup {

    public AutoTeste(Drivetrain drivetrain, TrajectoryBuilder trajectoryBuilder) {
        super.addCommands(

                trajectoryBuilder.build(true, "1-forward"),
                new WaitCommand(2),
                trajectoryBuilder.build(false, "1-reverse")

        );
    }
}