package frc.robot.commands.climber;

import frc.robot.constants.IntakeMoveConstants;
import frc.robot.subsystems.Climber;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

public class ChangeClimberSize extends Command {
  private Climber climber;
  private DoubleSupplier speed;

  public ChangeClimberSize(DoubleSupplier speed, Climber climber) {
    this.climber = climber;
    this.speed = speed;

    addRequirements(this.climber);
  }

  @Override
  public void execute() {
       if (this.climber.isLimit() && speed.getAsDouble() < 0) {
      this.climber.stop();
    } else {
      this.climber.set(this.speed.getAsDouble());
    }
  }

  @Override
  public void end(boolean interrupted) {
    this.climber.stop();
  }
}