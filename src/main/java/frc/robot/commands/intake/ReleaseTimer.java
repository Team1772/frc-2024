package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class ReleaseTimer extends Command {
  private final Intake intake;
  private final double secondsEnabled;
  private final double secondsEnabledUntilRelease;
  private Timer timer;

  public ReleaseTimer(Intake intake, double secondsEnabled, double secondsEnabledUntilRelease) {
    this.intake = intake;
    this.secondsEnabled = secondsEnabled;
    this.secondsEnabledUntilRelease = secondsEnabledUntilRelease;
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
    if(this.timer.hasElapsed(secondsEnabledUntilRelease)) {this.intake.set(IntakeConstants.Speeds.speedRelease);}
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(secondsEnabled);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    this.timer.stop();
  }
}
