package frc.robot.commands.drivetrain;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class BackwardTimer extends Command {
  private Drivetrain drivetrain;
  private Timer timer;

  private double seconds;

  public BackwardTimer(Drivetrain drivetrain, double seconds) {
    this.drivetrain = drivetrain;

    this.timer = new Timer();
    this.seconds = seconds;

    addRequirements(this.drivetrain);
  }

  @Override
  public void initialize() {
    this.timer.reset();
    this.timer.start();
}

  @Override
  public void execute() {
    this.drivetrain.arcadeDrive(0.6, 0);
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(seconds);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.drivetrain.arcadeDrive(0, 0);
    this.timer.stop();
  }
}