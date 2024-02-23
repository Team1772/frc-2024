package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class Release extends Command {
  private final Intake intake;
  private double speed;

  public Release(Intake intake) {
    this.intake = intake;
    this.speed = IntakeConstants.Speeds.speedRelease;

    addRequirements(this.intake);
  }

    public Release(Intake intake, double speed) {
    this.intake = intake;
    this.speed = speed;

    addRequirements(this.intake);
  }
  
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.set(this.speed);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
  }
}
