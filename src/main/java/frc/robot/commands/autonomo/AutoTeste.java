package frc.robot.commands.autonomo;

import frc.core.util.TrajectoryBuilder;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoTeste extends SequentialCommandGroup {

    public AutoTeste(Drivetrain drivetrain, TrajectoryBuilder trajectoryBuilder) {
        super.addCommands(
            
        trajectoryBuilder.build(true,"1"),
        trajectoryBuilder.build(false,"2")
        
        );
    }
}