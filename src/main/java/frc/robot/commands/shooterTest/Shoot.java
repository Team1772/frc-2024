package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterTest;

public class Shoot extends Command {

  private ShooterTest shooter;
  private double speed;
  private double speedDown;
  private double speedUp;

  public Shoot(ShooterTest shooter, double speedUp, double speedDown) {
    this.shooter = shooter;
    this.speedUp = speedUp;
    this.speedDown= speedDown;
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    this.shooter.shoot(speedUp, speedDown);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}