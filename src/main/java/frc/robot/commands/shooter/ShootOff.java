package frc.robot.commands.shooter;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

public class ShootOff extends Command {

  private final Shooter shooter;

  public ShootOff(Shooter shooter) {
    this.shooter = shooter;

    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    shooter.stop();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    shooter.stop();
  }
}
