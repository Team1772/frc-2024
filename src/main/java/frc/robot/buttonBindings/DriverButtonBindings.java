package frc.robot.buttonBindings;

import frc.core.util.oi.SmartController;

public class DriverButtonBindings {
    private SmartController driver = new SmartController(0);

    /* public void get() {
        //FACE BUTTONS
        this.driver.whileXButton(null);
        this.driver.whileAButton(null);
        this.driver.whileBButton(null);
        this.driver.whileYButton(null);

        //D-PAD
        this.driver.whileXUp(null);
        this.driver.whileXDown(null);
        this.driver.whileXRight(null);
        this.driver.whileXLeft(null);

        //SHOULDER BUTTONS
        this.driver.whileLeftBumper(null);
        this.driver.whileRightBumper(null);

        //START AND SELECT
        this.driver.whileStartButton(null);
        this.driver.whileSelectButton(null);
    } */

        public void get() {
        //BOOLEAN VALUES
        //FACE BUTTONS
        this.driver.getXButton();
        this.driver.getAButton();
        this.driver.getBButton();
        this.driver.getYButton();

        //D-PAD
        this.driver.getXUp();
        this.driver.getXDown();
        this.driver.getXRight();
        this.driver.getXLeft();

        //SHOULDER BUTTONS
        this.driver.getLeftBumper();
        this.driver.getRightBumper();

        //START AND SELECT
        this.driver.getStartButton();
        this.driver.getSelectButton();

        //DOUBLE VALUES
        //TRIGGERS
        this.driver.getLeftTrigger();
        this.driver.getRightTrigger();

        //JOYSTICKS
        this.driver.getRightX();
        this.driver.getRightY();
        this.driver.getLeftX();
        this.driver.getLeftY();
    }
}
