package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.util.oi.SmartController;

public class OperatorRumble extends SubsystemBase {
    private SmartController operator;
    private DigitalInput infraredSensor;

    public void sensorRumble(SmartController operator) {
        this.operator = operator;
        this.infraredSensor = new DigitalInput(3);
    }

    public boolean isLimit() {
        return !infraredSensor.get();
    }

    public void rumble() {
        if (this.isLimit()) {
            this.operator.enableRumble();
        } else {
            this.operator.disableRumble();
        }
    }

}
