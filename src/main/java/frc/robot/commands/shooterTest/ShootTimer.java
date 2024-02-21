package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterTest;

public class ShootTimer extends Command {

  private ShooterTest shooter;
  private final double secondsEnabled;
  private Timer timer;
  private double speedDown;
  private double speedUp;

  public ShootTimer(ShooterTest shooter, double speedUp, double speedDown, double secondsEnabled) {
    this.shooter = shooter;
    this.speedUp = speedUp;
    this.speedDown = speedDown;
    this.secondsEnabled = secondsEnabled;

    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    this.shooter.set(speedUp, speedDown);
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(secondsEnabled);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}