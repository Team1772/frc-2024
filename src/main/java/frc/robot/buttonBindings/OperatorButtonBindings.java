package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;

public class OperatorButtonBindings {
    private SmartController operator = new SmartController(1);

    /* public void get() {
        //FACE BUTTONS
        this.operator.whileXButton(null);
        this.operator.whileAButton(null);
        this.operator.whileBButton(null);
        this.operator.whileYButton(null);

        //D-PAD
        this.operator.whileXUp(null);
        this.operator.whileXDown(null);
        this.operator.whileXRight(null);
        this.operator.whileXLeft(null);

        //SHOULDER BUTTONS
        this.operator.whileLeftBumper(null);
        this.operator.whileRightBumper(null);

        //START AND SELECT
        this.operator.whileStartButton(null);
        this.operator.whileSelectButton(null);
    } */

        public void get() {
        //BOOLEAN VALUES
        //FACE BUTTONS
        this.operator.getXButton();
        this.operator.getAButton();
        this.operator.getBButton();
        this.operator.getYButton();

        //D-PAD
        this.operator.getXUp();
        this.operator.getXDown();
        this.operator.getXRight();
        this.operator.getXLeft();

        //SHOULDER BUTTONS
        this.operator.getLeftBumper();
        this.operator.getRightBumper();

        //START AND SELECT
        this.operator.getStartButton();
        this.operator.getSelectButton();

        //DOUBLE VALUES
        //TRIGGERS
        this.operator.getLeftTrigger();
        this.operator.getRightTrigger();

        //JOYSTICKS
        this.operator.getRightX();
        this.operator.getRightY();
        this.operator.getLeftX();
        this.operator.getLeftY();
    }
}
