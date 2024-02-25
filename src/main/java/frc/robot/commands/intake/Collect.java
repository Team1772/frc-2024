package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.oi.SmartController;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class Collect extends Command {
  private final Intake intake;
  private SmartController operator;
  private SmartController driver;

  public Collect(Intake intake, SmartController driver, SmartController operator) {
    this.intake = intake;
    this.driver = driver;
    this.operator = operator;
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.intake.set(IntakeConstants.Speeds.speedCollect);

    if (intake.isInfraredSensor()) {
      driver.enableRumble();
      operator.enableRumble();
    } else {
      driver.disableRumble();
      operator.disableRumble();
    }

    /*
     * if (intake.isInfraredSensor()) {
     * controller.timerRumble(4);
     * } else {
     * controller.disableRumble();
     * }
     */

  }

  @Override
  public void end(boolean isInterrupted) {
    this.intake.stop();
    operator.disableRumble();
    driver.disableRumble();
  }
}