// package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkBase.IdleMode;
// import com.revrobotics.CANSparkLowLevel.MotorType;

// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class DriveNeoTest extends SubsystemBase {
// private CANSparkMax motorLeftBack;
//   private CANSparkMax motorLeftFront;
//   private CANSparkMax motorRightBack;
//   private CANSparkMax motorRightFront;
//   private final DifferentialDrive drive;

//   public DriveNeoTest() {
//     this.motorLeftBack = new CANSparkMax(11, MotorType.kBrushless);
//     this.motorLeftFront = new CANSparkMax(12, MotorType.kBrushless);

//     this.motorRightBack = new CANSparkMax(13, MotorType.kBrushless);
//     this.motorRightFront = new CANSparkMax(14, MotorType.kBrushless);

//     this.motorRightFront.setInverted(true);
//     this.motorLeftFront.setInverted(false);

//     //this.motorLeftBack.restoreFactoryDefaults();
//     //this.motorLeftFront.restoreFactoryDefaults();
//     //this.motorRightBack.restoreFactoryDefaults();
//     //this.motorRightFront.restoreFactoryDefaults();

//     //this.motorLeftBack.setIdleMode(IdleMode.kCoast);
//     //this.motorLeftFront.setIdleMode(IdleMode.kCoast);
//     //this.motorRightBack.setIdleMode(IdleMode.kCoast);
//     //.motorRightFront.setIdleMode(IdleMode.kCoast);

//     this.motorRightBack.follow(this.motorRightFront);
//     this.motorLeftBack.follow(this.motorLeftFront);

//     this.drive = new DifferentialDrive(this.motorRightFront, this.motorLeftFront);

// }

// public void arcadeDrive(double forward, double rotation) {
//   this.drive.arcadeDrive(forward, rotation);
// }

// }