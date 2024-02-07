package frc.robot.commands.intake_move;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeMoveConstants;
import frc.robot.subsystems.IntakeMove;

public class IntakeDownToSensor extends Command {
  private IntakeMove intakeMove;
  private double speed;
  
  public IntakeDownToSensor(IntakeMove intakeMove) {
    this.intakeMove = intakeMove;
    this.speed = IntakeMoveConstants.Speed.speedDown;

    addRequirements(this.intakeMove);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intakeMove.set(this.speed);
  }
  
  @Override
  public boolean isFinished() {
    return this.intakeMove.isLimitMin();
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intakeMove.stop();
  }

}