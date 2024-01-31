package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterTest extends SubsystemBase {
  private final WPI_TalonSRX motorUp;
  private final WPI_TalonSRX motorDown;

  public ShooterTest(){
    this.motorUp = new WPI_TalonSRX(5);
    this.motorDown = new WPI_TalonSRX(6);
    this.motorDown.setInverted(true);
  }

  public void shoot(double speedUp, double speedDown){
    motorUp.set(speedUp);
    motorDown.set(speedDown);
  }

  public void stop() {
		motorUp.set(0);
    motorDown.set(0);
	}
}
