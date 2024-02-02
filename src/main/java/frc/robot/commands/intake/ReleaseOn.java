package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class ReleaseOn extends Command {
  private final Intake intake;

  public ReleaseOn(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    this.intake.set(IntakeConstants.Speeds.speedRelease);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean isInterrupted) {
  }
}
