package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.Led;
import frc.robot.subsystems.Shooter;

public class Shoot extends Command {

  private Shooter shooter;
  private double speedDown;
  private double speedUp;

  public Shoot(Shooter shooter, double speedUp, double speedDown) {
    this.shooter = shooter;
    this.speedUp = speedUp;
    this.speedDown = speedDown;
    addRequirements(this.shooter);
  }

  public Shoot(Shooter shooter, double speed) {
    this.shooter = shooter;
    this.speedUp = speed;
    this.speedDown = speed;
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    this.shooter.setPercentOutput(speedUp, speedDown);
    if(speedUp == 0.75) {
      Led.identifier = 1;
    } else {
      Led.identifier = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}