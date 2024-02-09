package frc.robot.commands.intake_move;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeMoveConstants;
import frc.robot.subsystems.IntakeMove;

public class TestIntakeMove extends Command {
  private IntakeMove intakeMove;

  public TestIntakeMove(IntakeMove intakeMove) {
    this.intakeMove = intakeMove;

    addRequirements(this.intakeMove);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (this.intakeMove.isLimitMax()) {
      this.intakeMove.stop();
      this.intakeMove.set(-0.8);
    } else if (this.intakeMove.isLimitMin()) {
      this.intakeMove.stop();
      this.intakeMove.set(0.8);
    } else {
      this.intakeMove.set(0.8);
    }
  }

  @Override
  public void end(boolean isInterrupted) {
    SmartDashboard.putBoolean("[Test] IntakeMove", true);
    this.intakeMove.stop();
  }
}