package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class Off extends Command {
  private final Intake intake;

  public Off(Intake intake) {
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
