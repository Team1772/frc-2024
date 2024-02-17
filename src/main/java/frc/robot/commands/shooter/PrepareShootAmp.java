package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class PrepareShootAmp extends Command {
  private final Shooter shooter;
  private double speedUp;
  private double speedDown;

  public PrepareShootAmp(double speedUp, double speedDown, Shooter shooter) {
    this.shooter = shooter;
    this.speedUp = speedUp;
    this.speedDown = speedDown;

    addRequirements(this.shooter);
  }

  @Override
  public void execute() {
    this.shooter.set(speedUp, speedDown);
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }
}
