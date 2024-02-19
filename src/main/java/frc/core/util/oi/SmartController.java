package frc.core.util.oi;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class SmartController {

  public final XboxController controller;

  private final JoystickButton xButton;
  private final JoystickButton aButton;
  private final JoystickButton bButton;
  private final JoystickButton yButton;
  private final JoystickButton leftBumper;
  private final JoystickButton rightBumper;
  private final JoystickButton startButton;
  private final JoystickButton selectButton;
  private final POVButton xUp;
  private final POVButton xDown;
  private final POVButton xRight;
  private final POVButton xLeft;

  private final JoystickButton leftTrigger;

  private final JoystickButton rightTrigger;

  public SmartController(int id) {

    this.controller = new XboxController(id);
    this.xButton = new JoystickButton(controller, XboxController.Button.kX.value);
    this.aButton = new JoystickButton(controller, XboxController.Button.kA.value);
    this.bButton = new JoystickButton(controller, XboxController.Button.kB.value);
    this.yButton = new JoystickButton(controller, XboxController.Button.kY.value);
    this.leftBumper = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);
    this.rightBumper = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
    this.leftTrigger = new JoystickButton(controller, XboxController.Axis.kLeftTrigger.value);
    this.rightTrigger = new JoystickButton(controller, XboxController.Axis.kRightTrigger.value);
    this.startButton = new JoystickButton(controller, 6);
    this.selectButton = new JoystickButton(controller, 7);
    this.xUp = new POVButton(controller, 0);
    this.xDown = new POVButton(controller, 180);
    this.xRight = new POVButton(controller, 90);
    this.xLeft = new POVButton(controller, 270);
  }

  public XboxController getController() {
    return controller;
  }

  public JoystickButton getXButton() {
    return xButton;
  }

  public JoystickButton getAButton() {
    return aButton;
  }

  public JoystickButton getBButton() {
    return bButton;
  }

  public JoystickButton getYButton() {
    return yButton;
  }

  public JoystickButton getLeftBumper() {
    return leftBumper;
  }

  public JoystickButton getRightBumper() {
    return rightBumper;
  }

  public JoystickButton getStartButton() {
    return startButton;
  }

  public JoystickButton getSelectButton() {
    return selectButton;
  }

  public POVButton getXUp() {
    return xUp;
  }

  public POVButton getXDown() {
    return xDown;
  }

  public POVButton getXRight() {
    return xRight;
  }

  public POVButton getXLeft() {
    return xLeft;
  }

  // Face buttons
  public void whileXButton(Command command) {
    xButton.whileTrue(command);
  }

  public void whileAButton(Command command) {
    aButton.whileTrue(command);
  }

  public void whileBButton(Command command) {
    bButton.whileTrue(command);
  }

  public void whileYButton(Command command) {
    yButton.whileTrue(command);
  }

  // D-Pad
  public void whileXUp(Command command) {
    xUp.whileTrue(command);
  }

  public void whileXDown(Command command) {
    xDown.whileTrue(command);
  }

  public void whileXRight(Command command) {
    xRight.whileTrue(command);
  }

  public void whileXLeft(Command command) {
    xLeft.whileTrue(command);
  }

  // Shoudler buttons
  public void whileLeftBumper(Command command) {
    leftBumper.whileTrue(command);
  }

  public void whileRightBumper(Command command) {
    rightBumper.whileTrue(command);
  }

  // Triggers
  public double getLeftTrigger() {
    return controller.getLeftTriggerAxis();
  }

  public double getRightTrigger() {
    return controller.getRightTriggerAxis();
  }

  public void whileLeftTrigger(Command command) {
    leftTrigger.whileTrue(command);
  }

  public void whileRightTrigger(Command command) {
    rightTrigger.whileTrue(command);
  }


  // Start and select
  public void whileStartButton(Command command) {
    startButton.whileTrue(command);
  }

  public void whileSelectButton(Command command) {
    selectButton.whileTrue(command);
  }

  // Sticks
  public double getLeftX() {
    return controller.getLeftX();
  }

  public double getLeftY() {
    return controller.getLeftY();
  }

  public double getRightX() {
    return controller.getRightX();
  }

  public double getRightY() {
    return controller.getRightY();
  }

  public void enableRumble() {
    this.controller.setRumble(RumbleType.kBothRumble, 1);
  }

  public void disableRumble() {
    this.controller.setRumble(RumbleType.kBothRumble, 0);
  }

  public void timerRumble(int velocityMultiplier) {
    if ((int) (Timer.getFPGATimestamp() * velocityMultiplier) % 2 == 0) {
      this.controller.setRumble(RumbleType.kBothRumble, 1);
    } else {
      this.controller.setRumble(RumbleType.kBothRumble, 0);
    }
  }

}
