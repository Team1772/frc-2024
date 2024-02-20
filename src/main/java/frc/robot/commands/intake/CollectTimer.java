package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class CollectTimer extends Command {
  private final Intake intake;
  private final double secondsEnabled;
  private Timer timer;

  public CollectTimer(Intake intake, double secondsEnabled) {
    this.intake = intake;
    this.secondsEnabled = secondsEnabled;
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
    this.intake.set(IntakeConstants.Speeds.speedCollect);
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
