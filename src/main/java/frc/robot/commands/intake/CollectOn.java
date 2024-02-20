package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class CollectOn extends Command {
  private final Intake intake;

  public CollectOn(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    this.intake.set(0.6);
  }

  @Override
  public void end(boolean isInterrupted) {
  }
}
