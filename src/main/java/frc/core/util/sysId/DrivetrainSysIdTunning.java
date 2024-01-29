package frc.core.util.sysId;

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

public class DrivetrainSysIdTunning implements Subsystem {

        private final MutableMeasure<Voltage> m_appliedVoltage = mutable(Volts.of(0));
        private final MutableMeasure<Distance> m_distance = mutable(Meters.of(0));
        private final MutableMeasure<Velocity<Distance>> m_velocity = mutable(MetersPerSecond.of(0));
        
        private SysIdRoutine sysIdRoutine;
        private WPI_TalonSRX motorLeftBack, motorRightBack, motorLeftFront, motorRightFront;
        private Encoder encoderLeft;
        private Encoder encoderRight;

        public DrivetrainSysIdTunning(WPI_TalonSRX[] leftMotors, WPI_TalonSRX[] rightMotors, Encoder[] encoders) {
                this.motorLeftBack = leftMotors[0];
                this.motorLeftFront = leftMotors[1];
                this.motorRightBack = rightMotors[0];
                this.motorRightFront = rightMotors[1];
                this.encoderLeft = encoders[0];
                this.encoderRight = encoders[1];
        }

        public void enable() {
                this.sysIdRoutine = new SysIdRoutine(
                                new SysIdRoutine.Config(),
                                new SysIdRoutine.Mechanism(
                                                (Measure<Voltage> volts) -> {
                                                        motorLeftBack.setVoltage(-volts.in(Volts));
                                                        motorLeftFront.setVoltage(-volts.in(Volts));
                                                        motorRightBack.setVoltage(volts.in(Volts));
                                                        motorRightFront.setVoltage(volts.in(Volts));
                                                },

                                                log -> {
                                                        log.motor("drive-leftBack")
                                                                        .voltage(
                                                                                        m_appliedVoltage.mut_replace(
                                                                                                        motorLeftBack.get()
                                                                                                                        * RobotController
                                                                                                                                        .getBatteryVoltage(),
                                                                                                        Volts))
                                                                        .linearPosition(m_distance.mut_replace(
                                                                                        encoderLeft.getDistance(),
                                                                                        Meters))
                                                                        .linearVelocity(
                                                                                        m_velocity.mut_replace(
                                                                                                        encoderLeft.getRate(),
                                                                                                        MetersPerSecond));
                                                        log.motor("drive-leftFront")
                                                                        .voltage(
                                                                                        m_appliedVoltage.mut_replace(
                                                                                                        motorLeftFront.get()
                                                                                                                        * RobotController
                                                                                                                                        .getBatteryVoltage(),
                                                                                                        Volts))
                                                                        .linearPosition(m_distance.mut_replace(
                                                                                        encoderLeft.getDistance(),
                                                                                        Meters))
                                                                        .linearVelocity(
                                                                                        m_velocity.mut_replace(
                                                                                                        encoderLeft.getRate(),
                                                                                                        MetersPerSecond));
                                                        log.motor("drive-rightBack")
                                                                        .voltage(
                                                                                        m_appliedVoltage.mut_replace(
                                                                                                        motorRightBack.get()
                                                                                                                        * RobotController
                                                                                                                                        .getBatteryVoltage(),
                                                                                                        Volts))
                                                                        .linearPosition(m_distance.mut_replace(
                                                                                        encoderRight.getDistance(),
                                                                                        Meters))
                                                                        .linearVelocity(
                                                                                        m_velocity.mut_replace(
                                                                                                        encoderRight.getRate(),
                                                                                                        MetersPerSecond));
                                                        log.motor("drive-rightFront")
                                                                        .voltage(
                                                                                        m_appliedVoltage.mut_replace(
                                                                                                        motorRightFront.get()
                                                                                                                        * RobotController
                                                                                                                                        .getBatteryVoltage(),
                                                                                                        Volts))
                                                                        .linearPosition(m_distance.mut_replace(
                                                                                        encoderRight.getDistance(),
                                                                                        Meters))
                                                                        .linearVelocity(
                                                                                        m_velocity.mut_replace(
                                                                                                        encoderRight.getRate(),
                                                                                                        MetersPerSecond));
                                                },
                                                this));
        }

        public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
                return sysIdRoutine.quasistatic(direction);
        }

        public Command sysIdDynamic(SysIdRoutine.Direction direction) {
                return sysIdRoutine.dynamic(direction);
        }

}
