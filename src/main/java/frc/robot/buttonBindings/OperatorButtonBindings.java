package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Drivetrain;

public class OperatorButtonBindings {
  private SmartController operator = new SmartController(ControllerConstants.kOperatorControllerPort);
  public Drivetrain drivetrain;

  public OperatorButtonBindings(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
  }
}
