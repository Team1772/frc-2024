package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class SwerveDrive extends CommandBase {

    private SwerveSubsystem swerve;
    private double SPEED = 0.3; 

    public SwerveDrive(SwerveSubsystem swerve)  {
        this.swerve = swerve;
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        swerve.getBackLeftSwerveModule().setDriveVelocity(SPEED);
        swerve.getFrontLeftSwerveModule().setDriveVelocity(SPEED);
        swerve.getBackRightSwerveModule().setDriveVelocity(SPEED);
        swerve.getFrontRightSwerveModule().setDriveVelocity(SPEED);
      
    }

    @Override
    public void end(boolean interrupted) {
        swerve.getBackLeftSwerveModule().driveVelocityStop();
        swerve.getFrontLeftSwerveModule().driveVelocityStop();
        swerve.getBackRightSwerveModule().driveVelocityStop();
        swerve.getFrontRightSwerveModule().driveVelocityStop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
