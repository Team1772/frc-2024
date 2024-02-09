package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeStop extends Command {
  private final Intake intake;

  public IntakeStop(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    this.intake.stop();
  }

  @Override
  public void end(boolean isInterrupted) {
  }
}
