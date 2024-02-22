package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;

public class Climber extends SubsystemBase {
  
    private WPI_VictorSPX motor;
    private DigitalInput limitSensor;
  
    public Climber() {
      this.motor = new WPI_VictorSPX(20);
      this.motor.setInverted(false);
      this.motor.setNeutralMode(NeutralMode.Brake);

      this.limitSensor = new DigitalInput(IntakeConstants.Sensor.climber);
    }

    public boolean isLimit() {
      return limitSensor.get();
    }
  
    public void set(double speed) {
      this.motor.set(speed);
    }
  
    public void stop() {
      this.motor.set(0);
    }

    public void periodic() {
      SmartDashboard.putBoolean("is Climber Limit Sensor", isLimit());
    }

  }
  