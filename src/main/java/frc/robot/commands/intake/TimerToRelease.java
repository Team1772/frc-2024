package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class TimerToRelease extends Command {
  private final Intake intake;
  private Timer timer;
  private double secondsToFinish;
  private double secondsToStart;

  public TimerToRelease(Intake intake, double secondsToStart, double secondsToFinish) {
    this.intake = intake;
    this.secondsToStart = secondsToStart;
    this.secondsToFinish = secondsToFinish;
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
    if (!timer.hasElapsed(secondsToStart)) {
      this.intake.set(0);

    } else {
      this.intake.set(IntakeConstants.Speeds.speedRelease);

    }
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(secondsToFinish);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    this.timer.stop();
  }
}
