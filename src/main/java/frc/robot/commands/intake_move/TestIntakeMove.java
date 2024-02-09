package frc.robot.commands.intake_move;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeMoveConstants;
import frc.robot.subsystems.IntakeMove;

public class TestIntakeMove extends Command {
  private IntakeMove intakeMove;
  private Boolean limitMax;
  private Boolean isLimitMin;

  public TestIntakeMove(IntakeMove intakeMove) {
    this.intakeMove = intakeMove;
    this.limitMax = false;
    this.isLimitMin = false;
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
      this.limitMax = true;
    } else if (this.intakeMove.isLimitMin()) {
      this.intakeMove.stop();
      this.intakeMove.set(0.8);
      this.isLimitMin = true;
    } else {
      this.intakeMove.set(0.8);
    }
    if (this.isLimitMin && this.limitMax) {
      SmartDashboard.putBoolean("[Test] IntakeMove", true);
    }
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intakeMove.stop();
  }
}