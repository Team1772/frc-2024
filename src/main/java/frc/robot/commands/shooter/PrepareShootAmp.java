package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class PrepareShootAmp extends Command {
  private final Shooter shooter;
  private double upperSpeed;
  private double lowerSpeed;

  public PrepareShootAmp(double upperMeterPerSecond, double lowerVoltage, Shooter shooter) {
    this.shooter = shooter;
    this.upperSpeed = upperMeterPerSecond;
    this.lowerSpeed = lowerVoltage;

    addRequirements(this.shooter);
  }

  @Override
  public void execute() {
    this.shooter.setUpperVelocityMetersPerSecondandLowerVoltage(upperSpeed, lowerSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}
