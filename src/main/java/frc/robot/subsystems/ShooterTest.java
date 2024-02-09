package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ShooterTestConstants;

public class ShooterTest extends SubsystemBase {
  private final WPI_TalonSRX motorUpper;
  private final WPI_TalonSRX motorLower;

  public ShooterTest(){
    this.motorUpper = new WPI_TalonSRX(ShooterTestConstants.Motors.motorUpper);
    this.motorLower = new WPI_TalonSRX(ShooterTestConstants.Motors.motorLower);
    this.motorUpper.setNeutralMode(NeutralMode.Coast);
    this.motorLower.setNeutralMode(NeutralMode.Coast);
    this.motorUpper.setInverted(ShooterTestConstants.Motors.isMotorUpperInverted);
    this.motorLower.setInverted(ShooterTestConstants.Motors.isMotorLowerInverted);
  }

  public void set(double speedUp, double speedDown){
    motorUpper.set(speedUp);
    motorLower.set(speedDown);
  }
  public void set(double speed){
    motorUpper.set(speed);
    motorLower.set(speed);
  }
  
  public void stop() {
		motorUpper.set(0);
    motorLower.set(0);
	}
}
