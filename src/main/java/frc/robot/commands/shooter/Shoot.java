package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.Led;
import frc.robot.subsystems.Shooter;

public class Shoot extends Command {

  private Shooter shooter;
  private double speed;

  public Shoot(Shooter shooter, double speed) {
    this.shooter = shooter;
    this.speed = speed;
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    this.shooter.setVelocityMetersPerSecond(speed);
    if(speed >= 18) {
      //Led.identifier = 1;
    } else {
      //Led.identifier = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}