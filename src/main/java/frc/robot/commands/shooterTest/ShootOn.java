package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterTest;

public class ShootOn extends Command {

  private ShooterTest shooter;
  private double speedDown;
  private double speedUp;

  public ShootOn(ShooterTest shooter, double speed) {
    this.shooter = shooter;
    this.speedDown = speed;
    this.speedUp = speed;
    addRequirements(this.shooter);
  }

  public ShootOn(ShooterTest shooter, double speedUp, double speedDown) {
    this.shooter = shooter;
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {
    this.shooter.set(speedUp, speedDown);
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {

  }
}