package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private final WPI_TalonSRX motor;

  public Intake(){
    this.motor = new WPI_TalonSRX(8);
    this.motor.setNeutralMode(NeutralMode.Coast);
  }

  public void set(double speed){
    motor.set(speed);
  }

  public void stop() {
		motor.set(0);
	}
}
