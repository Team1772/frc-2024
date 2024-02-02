package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.intake.Off;
import frc.robot.commands.intake.ReleaseOn;
import frc.robot.commands.intake_move.IntakeUpToSensor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.ShooterTest;

public class ShootAndIntakeSpeaker extends SequentialCommandGroup {

  public ShootAndIntakeSpeaker(ShooterTest shooter, Intake intake, IntakeMove intakeMove) {
    super.addCommands(
      new IntakeUpToSensor(intakeMove),
      new ShootOn(shooter, 0.8),
      new WaitCommand(2),
      new ReleaseOn(intake),
      new WaitCommand(3),
      new Off(intake)

    );
  }
}
