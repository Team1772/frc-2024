package frc.robot.commands.intake_move;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeMoveConstants;
import frc.robot.subsystems.IntakeMove;

public class IntakeUpDown extends Command {
  private IntakeMove intakeMove;
  private DoubleSupplier speed;

  public IntakeUpDown(IntakeMove intakeMove, DoubleSupplier speed) {
    this.intakeMove = intakeMove;
    this.speed = speed;

    addRequirements(this.intakeMove);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (this.intakeMove.isLimitMax() && speed.getAsDouble() > 0) {
      this.intakeMove.stop();
    } else if (this.intakeMove.isLimitMin() && speed.getAsDouble() < 0) {
      this.intakeMove.stop();
    } else {
      this.intakeMove.set(IntakeMoveConstants.Speed.speedMultiplier * speed.getAsDouble());
    }
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intakeMove.stop();
  }
}