package frc.robot.commands.shooter;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

public class ShootOn extends Command {

  private final Shooter shooter;
    
  private double shootVelocity;

  public ShootOn(Shooter shooter, double shootVelocity) {
    this.shooter = shooter;
    this.shootVelocity = shootVelocity;
    
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    shooter.setPercentOutput(shootVelocity);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }
}
