package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ShooterTestConstants;
import frc.robot.subsystems.ShooterTest;

public class ShootManual extends Command {

  private ShooterTest shooter;
  private double speed;

  public ShootManual(ShooterTest shooter, double speed) {
    this.shooter = shooter;
    this.speed = speed;
    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    this.shooter.set(this.speed * ShooterTestConstants.Speed.speedMultiplier);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}