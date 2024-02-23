package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.intake.ReleaseTimer;
import frc.robot.commands.intake_move.IntakeDownToSensor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;

public class SmartCollectTimer extends ParallelCommandGroup {
  private Intake intake;
  private IntakeMove intakeMove;

  private double secondsEnabled;

  public SmartCollectTimer(double secondsEnabled, Intake intake, IntakeMove intakeMove) {
    this.intake = intake;
    this.intakeMove = intakeMove;
    this.secondsEnabled = secondsEnabled;

    super.addCommands(
      new IntakeDownToSensor(intakeMove),
      new SmartCollect(intake)
    );

    super.addRequirements(this.intake, this.intakeMove);
  }

  // @Override
  // public void end(boolean isInterrupted) {
  //   this.intake.stop();
  //   this.shooter.stop();
  // }
}