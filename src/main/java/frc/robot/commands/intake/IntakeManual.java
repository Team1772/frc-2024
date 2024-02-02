package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class IntakeManual extends Command {
  private final Intake intake;
  private double speed;

  public IntakeManual(Intake intake, double speed) {
    this.intake = intake;
    this.speed = speed;
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.intake.set(this.speed * IntakeConstants.Speeds.speedMultiplier);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
  }
}