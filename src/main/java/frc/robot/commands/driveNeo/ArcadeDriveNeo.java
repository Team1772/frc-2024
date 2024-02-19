// package frc.robot.commands.driveNeo;

// import frc.core.util.oi.SmartController;
// import frc.robot.subsystems.DriveNeoTest;
// import frc.robot.subsystems.Drivetrain;

// import java.util.function.DoubleSupplier;

// import edu.wpi.first.wpilibj2.command.Command;

// public class ArcadeDriveNeo extends Command {
//   private DriveNeoTest drivetrain;
//   private DoubleSupplier forward;
//   private DoubleSupplier rotation;
//   private SmartController driver;

//   private static final double DRIVE_LIMITER = 0.5;

//   public ArcadeDriveNeo(DriveNeoTest drivetrain, DoubleSupplier forward, DoubleSupplier rotation, SmartController driver) {
//     this.drivetrain = drivetrain;
//     this.forward = forward;
//     this.rotation = rotation;
//     this.driver = driver;

//     addRequirements(this.drivetrain);
//   }



//   @Override
//   public void execute() {

//     if (this.driver.getLeftBumper().getAsBoolean()){
//       this.drivetrain.arcadeDrive(this.forward.getAsDouble() * DRIVE_LIMITER, this.rotation.getAsDouble());
//     } else {
//       this.drivetrain.arcadeDrive(this.forward.getAsDouble(), this.rotation.getAsDouble());
//     }
//   }
// }