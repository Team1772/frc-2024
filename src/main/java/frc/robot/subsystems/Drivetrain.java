package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static edu.wpi.first.units.MutableMeasure.mutable;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.core.components.Limelight;
import frc.core.components.SmartNavX;
import frc.robot.constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

  private static final boolean IS_DEBUGGING = true;

  private WPI_TalonSRX motorLeftBack;
  private WPI_TalonSRX motorLeftFront;
  private WPI_TalonSRX motorRightBack;
  private WPI_TalonSRX motorRightFront;

  private final MotorControllerGroup motorsRight;
  private final MotorControllerGroup motorsLeft;
  private final DifferentialDrive drive;
  private Encoder encoderRight;
  private Encoder encoderLeft;
  private final SmartNavX navX;
  private final DifferentialDriveOdometry odometry;

  // Mutable holder for unit-safe voltage values, persisted to avoid reallocation.
  private final MutableMeasure<Voltage> m_appliedVoltage = mutable(Volts.of(0));
  // Mutable holder for unit-safe linear distance values, persisted to avoid
  // reallocation.
  private final MutableMeasure<Distance> m_distance = mutable(Meters.of(0));
  // Mutable holder for unit-safe linear velocity values, persisted to avoid
  // reallocation.
  private final MutableMeasure<Velocity<Distance>> m_velocity = mutable(MetersPerSecond.of(0));

  private final SysIdRoutine sysIdRoutine; 
  public Drivetrain() {
    this.motorLeftBack = new WPI_TalonSRX(DrivetrainConstants.Motors.motorLeftBack);
    this.motorLeftFront = new WPI_TalonSRX(DrivetrainConstants.Motors.motorLeftFront);
    this.motorsLeft = new MotorControllerGroup(motorLeftBack, motorLeftFront);

    this.motorRightBack = new WPI_TalonSRX(DrivetrainConstants.Motors.motorRightBack);
    this.motorRightFront = new WPI_TalonSRX(DrivetrainConstants.Motors.motorRightFront);
    this.motorsRight = new MotorControllerGroup(motorRightBack, motorRightFront);

    this.motorLeftBack.setNeutralMode(NeutralMode.Coast);
    this.motorRightBack.setNeutralMode(NeutralMode.Coast);
    this.motorLeftFront.setNeutralMode(NeutralMode.Coast);
    this.motorRightFront.setNeutralMode(NeutralMode.Coast);

    this.setMotorsInverted(
        DrivetrainConstants.Motors.isMotorsLeftInverted,
        DrivetrainConstants.Motors.isMotorsRightInverted);

    this.drive = new DifferentialDrive(this.motorsRight, this.motorsLeft);

    this.encoderLeft = new Encoder(
        DrivetrainConstants.Encoders.encoderLeftPortOne,
        DrivetrainConstants.Encoders.encoderLeftPortTwo,
        DrivetrainConstants.Encoders.isEncoderLeftInverted);

    this.encoderRight = new Encoder(
        DrivetrainConstants.Encoders.encoderRightPortOne,
        DrivetrainConstants.Encoders.encoderRightPortTwo,
        DrivetrainConstants.Encoders.isEncoderRightInverted);

    this.navX = new SmartNavX();

    this.odometry = new DifferentialDriveOdometry(
        this.getRotation2d(),
        0,
        0);

    this.setEncodersDistancePerPulse();
    this.resetEncoders();

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

  public void arcadeDrive(double forward, double rotation) {
    this.drive.arcadeDrive(forward, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    this.drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    this.motorsLeft.setVoltage(leftVolts);
    this.motorsRight.setVoltage(rightVolts);

    this.drive.feed();
  }

  public void setMaxOutput(double maxOutput) {
    this.drive.setMaxOutput(maxOutput);
  }

  public void resetEncoders() {
    this.encoderLeft.reset();
    this.encoderRight.reset();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(
        this.encoderLeft.getRate(),
        this.encoderRight.getRate());
  }

  public double getAverageDistance() {
    return (this.encoderLeft.getDistance() + this.encoderRight.getDistance()) / 2.0;
  }

  public void resetNavX() {
    this.navX.reset();
  }

  public void reset() {
    this.resetNavX();
    this.resetEncoders();
  }

  public double getAngle() {
    return this.navX.getAngle();
  }

  public Rotation2d getRotation2d() {
    return this.navX.getRotation2d();
  }

  public Pose2d getPose() {
    return this.odometry.getPoseMeters();
  }

  public void updateOdometry() {
    this.odometry.update(
        this.getRotation2d(),
        this.encoderLeft.getDistance(),
        this.encoderRight.getDistance());
  }

  public void resetOdometry(Pose2d pose) {
    this.resetEncoders();

    /*
     * CAUTION! MAY NOT WORK YET
     * SEE WHAT WE SHOULD PUT IN PARAMS
     */
    this.odometry.resetPosition(
        this.getRotation2d(),
        this.getEncoderLeft().get(),
        this.getEncoderRight().get(),
        pose);
  }

  public Encoder getEncoderLeft() {
    return this.encoderLeft;
  }

  public Encoder getEncoderRight() {
    return this.encoderRight;
  }

  public void setMotorsInverted(boolean isMotorsLeftInverted, boolean isMotorsRightInverted) {
    this.motorsLeft.setInverted(isMotorsLeftInverted);
    this.motorsRight.setInverted(isMotorsRightInverted);
  }

  public void setEncodersDistancePerPulse() {
    var wheelCircumferenceMeters = Units.inchesToMeters(DrivetrainConstants.Chassi.wheelRadius) * 2 * Math.PI;

    var distancePerPulse = wheelCircumferenceMeters / DrivetrainConstants.Encoders.pulsesPerRotation;

    this.encoderLeft.setDistancePerPulse(distancePerPulse);
    this.encoderRight.setDistancePerPulse(distancePerPulse);
  }

  public void setBrakeMode() {
    this.motorLeftBack.setNeutralMode(NeutralMode.Brake);
    this.motorLeftFront.setNeutralMode(NeutralMode.Brake);
    this.motorRightBack.setNeutralMode(NeutralMode.Brake);
    this.motorRightFront.setNeutralMode(NeutralMode.Brake);
  }

    public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
    return sysIdRoutine.quasistatic(direction);
  }

  public Command sysIdDynamic(SysIdRoutine.Direction direction) {
    return sysIdRoutine.dynamic(direction);
  }

  public void debugSmartDashboard(boolean isDebugging) {
    if (isDebugging) {
      SmartDashboard.putNumber("[DRIVETRAIN] Encoder Left", this.encoderLeft.get());
      SmartDashboard.putNumber("[DRIVETRAIN] Encoder Right", this.encoderRight.get());
      SmartDashboard.putNumber("[DRIVETRAIN] Average Distance", this.getAverageDistance());

      SmartDashboard.putNumber("[DRIVETRAIN] Altitude", this.navX.getAltitude());
      SmartDashboard.putNumber("[DRIVETRAIN] Pitch", this.navX.getPitch());
      SmartDashboard.putNumber("[DRIVETRAIN] Angle", this.navX.getAngle());
      SmartDashboard.putNumber("[LIMELIGHT] X-axis", Limelight.getX());
      SmartDashboard.putNumber("[LIMELIGHT] Y-axis", Limelight.getY());
      SmartDashboard.putNumber("[LIMELIGHT] Target Area", Limelight.getA());
      SmartDashboard.putNumber("[LIMELIGHT] Is On Target", Limelight.getV());

      SmartDashboard.putNumber("[MLB] getOutputCurrent", motorLeftBack.getOutputCurrent());
      SmartDashboard.putNumber("[MLB]Get Motor output voltage", motorLeftBack.getMotorOutputVoltage());
      SmartDashboard.putNumber("[MLB] getBusVoltage", motorLeftBack.getBusVoltage());
      SmartDashboard.putNumber("[MLB] getSupplyCurrent", motorLeftBack.getSupplyCurrent());
      SmartDashboard.putNumber("[MLB] getBusVoltage", motorLeftBack.getStatorCurrent());

      SmartDashboard.putNumber("[MLF] getOutputCurrent", motorLeftFront.getOutputCurrent());
      SmartDashboard.putNumber("[MLF]Get Motor output voltage", motorLeftFront.getMotorOutputVoltage());
      SmartDashboard.putNumber("[MLF] getBusVoltage", motorLeftFront.getBusVoltage());
      SmartDashboard.putNumber("[MLF] getSupplyCurrent", motorLeftFront.getSupplyCurrent());
      SmartDashboard.putNumber("[MLF] getBusVoltage", motorLeftFront.getStatorCurrent());

      SmartDashboard.putNumber("[MRB] getOutputCurrent", motorRightBack.getOutputCurrent());
      SmartDashboard.putNumber("[MRB]Get Motor output voltage", motorRightBack.getMotorOutputVoltage());
      SmartDashboard.putNumber("[MRB] getBusVoltage", motorRightBack.getBusVoltage());
      SmartDashboard.putNumber("[MRB] getSupplyCurrent", motorRightBack.getSupplyCurrent());
      SmartDashboard.putNumber("[MRB] getBusVoltage", motorRightBack.getStatorCurrent());

      SmartDashboard.putNumber("[MRF] getOutputCurrent", motorRightFront.getOutputCurrent());
      SmartDashboard.putNumber("[MRF]Get Motor output voltage", motorRightFront.getMotorOutputVoltage());
      SmartDashboard.putNumber("[MRF] getBusVoltage", motorRightFront.getBusVoltage());
      SmartDashboard.putNumber("[MRF] getSupplyCurrent", motorRightFront.getSupplyCurrent());
      SmartDashboard.putNumber("[MRF] getBusVoltage", motorRightFront.getStatorCurrent());
    }
  }

  @Override
  public void periodic() {
    this.debugSmartDashboard(Drivetrain.IS_DEBUGGING);
    this.updateOdometry();
  }
}