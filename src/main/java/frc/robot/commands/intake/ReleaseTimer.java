package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class ReleaseTimer extends Command {
  private final Intake intake;
  private Timer timer;

  public ReleaseTimer(Intake intake) {
    this.intake = intake;
    this.timer = new Timer();

    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    this.timer.reset();
    this.timer.start();
  }

  @Override
  public void execute() {
     this.intake.set(IntakeConstants.Speeds.speedRelease);
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(1.5);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    this.timer.stop();
  }
}
