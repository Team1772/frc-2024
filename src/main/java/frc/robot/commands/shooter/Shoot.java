package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
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
    this.shooter.setVelocityMetersPerSecond(speedUp);
    //this.shooter.setPercentOutput(speedUp, speedDown);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stopPID();
  }
}