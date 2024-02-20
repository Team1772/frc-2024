package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  
    private WPI_VictorSPX motor;
  
    public Climber() {
      this.motor = new WPI_VictorSPX(20);
      this.motor.setInverted(false);
      this.motor.setNeutralMode(NeutralMode.Brake);
    }
  
    public void set(double speed) {
      this.motor.set(speed);
    }
  
    public void stop() {
      this.motor.set(0);
    }

  }
  