package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private final WPI_TalonSRX motor;
  private DigitalInput infraredSensor;

  public Intake(){
    this.motor = new WPI_TalonSRX(IntakeConstants.Motors.id);
    this.motor.setNeutralMode(NeutralMode.Coast);
    this.infraredSensor = new DigitalInput(IntakeConstants.Sensor.infraredSensor);
  }

  public void set(double speed){
    motor.set(speed);
  }

  public boolean isInfraredSensor() {
    return !infraredSensor.get();
  }

  public void stop() {
		motor.set(0);
	}

  public void periodic() {
    SmartDashboard.putBoolean("is InfraredSensor", isInfraredSensor());
  }
}
