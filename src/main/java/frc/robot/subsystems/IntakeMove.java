package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeMoveConstants;

public class IntakeMove extends SubsystemBase {
  
    private WPI_VictorSPX motorMove;
    private DigitalInput limitMax;
    private DigitalInput limitMin;
  
    public IntakeMove() {
      this.motorMove = new WPI_VictorSPX(IntakeMoveConstants.Motor.motorMove);
      this.motorMove.setInverted(IntakeMoveConstants.Motor.isMotorMoveInverted);
      this.limitMax = new DigitalInput(IntakeMoveConstants.Sensor.limitMax);
      this.limitMin = new DigitalInput(IntakeMoveConstants.Sensor.limitMin);
      this.motorMove.setNeutralMode(NeutralMode.Brake);
    }
  
    public void set(double speed) {
      this.motorMove.set(ControlMode.PercentOutput, speed);
    }

    public boolean isLimitMax(){
      return !this.limitMax.get();
    }

    public boolean isLimitMin(){
      return this.limitMin.get();
    }
  
    public void stop() {
      this.motorMove.set(0);
    }
  
    @Override
    public void periodic() {
      SmartDashboard.putBoolean("Is limit Max", isLimitMax());
      SmartDashboard.putBoolean("Is limit Min", isLimitMin());
    }
  }
  