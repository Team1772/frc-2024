package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.ReleaseToShoot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShootAutonomous extends SequentialCommandGroup {
    private Intake intake;
    private Shooter shooter;

    public ShootAutonomous(double secondsEnabled, Intake intake, Shooter shooter) {
      this.intake = intake;
      this.shooter = shooter;  
      super.addCommands(
          // new RollbackToShoot(this.intake, this.buffer, this.shooter),

          new ParallelRaceGroup(
            new ShootTimer(this.shooter, secondsEnabled),
            new ReleaseToShoot(intake, shooter)
          ) 
      );
  
      super.addRequirements(this.intake, this.shooter);
    }
}
