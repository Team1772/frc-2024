package frc.robot.commands.autonomo;

import frc.core.util.TrajectoryBuilder;
import frc.core.util.oi.SmartController;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.proto.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoTeste extends SequentialCommandGroup {

  public AutoTeste(Drivetrain drivetrain, TrajectoryBuilder trajectoryBuilder) {
    super.addCommands(

        trajectoryBuilder.build(true, "1"),
        trajectoryBuilder.build(false, "2")

    );
  }
}