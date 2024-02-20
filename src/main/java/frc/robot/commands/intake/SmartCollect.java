package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.oi.SmartController;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class SmartCollect extends Command {
  private final Intake intake;

  public SmartCollect(Intake intake) {
    this.intake = intake;
    
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.intake.set(IntakeConstants.Speeds.speedCollect);
}

  @Override
  public boolean isFinished() {
    return intake.isInfraredSensor();
}

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
  }
}