package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class CollectOff extends Command {
  private final Intake intake;

  public CollectOff(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
    this.intake.stop();
  }

  @Override
  public boolean isFinished() {
    return true;
  }


  @Override
  public void end(boolean isInterrupted) {
  }
}
