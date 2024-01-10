package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class SwerveTurn extends CommandBase {

    private SwerveSubsystem swerve;
    private double SPEED = 0.3; 

    public SwerveTurn(SwerveSubsystem swerve)  {
        this.swerve = swerve;
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        swerve.getBackLeftSwerveModule().setTurnVelocity(SPEED);
        swerve.getFrontLeftSwerveModule().setTurnVelocity(SPEED);
        swerve.getBackRightSwerveModule().setTurnVelocity(SPEED);
        swerve.getFrontRightSwerveModule().setTurnVelocity(SPEED);
      
    }

    @Override
    public void end(boolean interrupted) {
        swerve.getBackLeftSwerveModule().turnVelocityStop();
        swerve.getFrontLeftSwerveModule().turnVelocityStop();
        swerve.getBackRightSwerveModule().turnVelocityStop();
        swerve.getFrontRightSwerveModule().turnVelocityStop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
