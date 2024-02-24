package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootTimer extends Command {

  private Shooter shooter;
  private final double secondsEnabled;
  private Timer timer;
  private double speedDown;
  private double speedUp;

  public ShootTimer(Shooter shooter, double speedUp, double speedDown, double secondsEnabled) {
    this.shooter = shooter;
    this.speedUp = speedUp;
    this.speedDown = speedDown;
    this.secondsEnabled = secondsEnabled;
    this.timer = new Timer();

    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    this.shooter.setPercentOutput(speedUp, speedDown);
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(secondsEnabled);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stopPID();
  }
}