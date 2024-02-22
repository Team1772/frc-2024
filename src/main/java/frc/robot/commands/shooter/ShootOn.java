package frc.robot.commands.shooter;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

public class ShootOn extends Command {

  private final Shooter shooter;
    
  private double shootVelocityMetersPerSecond;

  public ShootOn(Shooter shooter, double shootVelocityMetersPerSecond) {
    this.shooter = shooter;
    this.shootVelocityMetersPerSecond = shootVelocityMetersPerSecond;
    
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(this.shooter.isSafetyShoot()) {
      this.shooter.setVelocityMetersPerSecond(this.shootVelocityMetersPerSecond);
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }
}
