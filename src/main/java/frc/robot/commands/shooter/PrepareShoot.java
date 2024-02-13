package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class PrepareShoot extends Command {
  private final Shooter shooter;
  private double speed;

  public PrepareShoot(double speed, Shooter shooter) {
    this.shooter = shooter;
    this.speed = speed;

    addRequirements(this.shooter);
  }

  @Override
  public void execute() {
    this.shooter.setVelocityMetersPerSecond(speed);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}
