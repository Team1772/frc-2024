package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeMoveConstants;

public class Climber extends SubsystemBase {
  
    private WPI_VictorSPX motor;
    private DigitalInput limitMin;
  
    public Climber() {
      this.motor = new WPI_VictorSPX(8);
      this.motor.setInverted(false);
      this.limitMin = new DigitalInput(IntakeMoveConstants.Sensor.limitMin);
      this.motor.setNeutralMode(NeutralMode.Coast);
    }
  
    public void set(double speed) {
      this.motor.set(ControlMode.PercentOutput, speed);
    }

    public boolean isLimitMin(){
      return this.limitMin.get();
    }
  
    public void stop() {
      this.motor.set(0);;
    }
  
    @Override
    public void periodic() {
      SmartDashboard.putBoolean("[Climber] Is limit Min", isLimitMin());
    }
  }
  