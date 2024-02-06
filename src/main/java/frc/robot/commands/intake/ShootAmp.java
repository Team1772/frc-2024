package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class ShootAmp extends Command {
  private final Intake intake;

  public ShootAmp(Intake intake) {
    this.intake = intake;

    addRequirements(this.intake);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.set(0.5);
  }

  

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
  }
}
