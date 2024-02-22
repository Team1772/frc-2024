package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ShooterConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ReleaseToShoot extends Command {
  private final Intake intake;
  private final Shooter shooter;
  private final Timer timer;
  private double secondsEnabled;

  public ReleaseToShoot(Intake intake, Shooter shooter, double secondsEnabled) {
    this.intake = intake;
    this.shooter = shooter;
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
    if (this.shooter.atSettedVelocity()) {
      this.intake.set(ShooterConstants.speed);
    }
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(this.secondsEnabled);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    this.timer.stop();
  }
}
