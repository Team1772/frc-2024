package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootMetersPerSecond extends Command {

  private Shooter shooter;
  private double meterPerSecond;

  public ShootMetersPerSecond(Shooter shooter, double meterPerSecond) {
    this.shooter = shooter;
    this.meterPerSecond = meterPerSecond;
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    this.shooter.setVelocityMetersPerSecond(meterPerSecond);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stopPID();
  }
}