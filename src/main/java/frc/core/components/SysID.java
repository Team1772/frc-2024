package frc.core.components;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import static edu.wpi.first.units.MutableMeasure.mutable;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SysID implements Subsystem {
    // Mutable holder for unit-safe voltage values, persisted to avoid reallocation.
    private final MutableMeasure<Voltage> m_appliedVoltage = mutable(Volts.of(0));
    // Mutable holder for unit-safe linear distance values, persisted to avoid
    // reallocation.
    private final MutableMeasure<Distance> m_distance = mutable(Meters.of(0));
    // Mutable holder for unit-safe linear velocity values, persisted to avoid
    // reallocation.
    private final MutableMeasure<Velocity<Distance>> m_velocity = mutable(MetersPerSecond.of(0));
    private SysIdRoutine sysIdRoutine;

    public SysID(WPI_TalonSRX motorLeftBack, WPI_TalonSRX motorLeftFront, WPI_TalonSRX motorRightBack,
            WPI_TalonSRX motorRightFront, Encoder encoderLeft, Encoder encoderRight) {
        this.sysIdRoutine = new SysIdRoutine(
                // Empty config defaults to 1 volt/second ramp rate and 7 volt step voltage.
                new SysIdRoutine.Config(),
                new SysIdRoutine.Mechanism(
                        // Tell SysId how to plumb the driving voltage to the motors.
                        (Measure<Voltage> volts) -> {
                            motorLeftBack.setVoltage(-volts.in(Volts));
                            motorLeftFront.setVoltage(-volts.in(Volts));
                            motorRightBack.setVoltage(volts.in(Volts));
                            motorRightFront.setVoltage(volts.in(Volts));
                        },
                        // Tell SysId how to record a frame of data for each motor on the mechanism
                        // being
                        // characterized.
                        log -> {
                            // Record a frame for the left motors. Since these share an encoder, we consider
                            // the entire group to be one motor.
                            log.motor("drive-leftBack")
                                    .voltage(
                                            m_appliedVoltage.mut_replace(
                                                    motorLeftBack.get() * RobotController.getBatteryVoltage(), Volts))
                                    .linearPosition(m_distance.mut_replace(encoderLeft.getDistance(), Meters))
                                    .linearVelocity(
                                            m_velocity.mut_replace(encoderLeft.getRate(), MetersPerSecond));
                            log.motor("drive-leftFront")
                                    .voltage(
                                            m_appliedVoltage.mut_replace(
                                                    motorLeftFront.get() * RobotController.getBatteryVoltage(), Volts))
                                    .linearPosition(m_distance.mut_replace(encoderLeft.getDistance(), Meters))
                                    .linearVelocity(
                                            m_velocity.mut_replace(encoderLeft.getRate(), MetersPerSecond));
                            // Record a frame for the right motors. Since these share an encoder, we
                            // consider
                            // the entire group to be one motor.
                            log.motor("drive-rightBack")
                                    .voltage(
                                            m_appliedVoltage.mut_replace(
                                                    motorRightBack.get() * RobotController.getBatteryVoltage(), Volts))
                                    .linearPosition(m_distance.mut_replace(encoderRight.getDistance(), Meters))
                                    .linearVelocity(
                                            m_velocity.mut_replace(encoderRight.getRate(), MetersPerSecond));
                            log.motor("drive-rightFront")
                                    .voltage(
                                            m_appliedVoltage.mut_replace(
                                                    motorRightFront.get() * RobotController.getBatteryVoltage(), Volts))
                                    .linearPosition(m_distance.mut_replace(encoderRight.getDistance(), Meters))
                                    .linearVelocity(
                                            m_velocity.mut_replace(encoderRight.getRate(), MetersPerSecond));
                        },
                        // Tell SysId to make generated commands require this subsystem, suffix test
                        // state in
                        // WPILog with this subsystem's name ("drive")
                        this));
    }

    public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
        return sysIdRoutine.quasistatic(direction);
    }

    public Command sysIdDynamic(SysIdRoutine.Direction direction) {
        return sysIdRoutine.dynamic(direction);
    }

}
