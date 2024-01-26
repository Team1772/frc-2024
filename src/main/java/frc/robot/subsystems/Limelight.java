package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.core.util.LimelightHelpers;

public class Limelight extends SubsystemBase {
    private final String limelightName;

    public Limelight(String limelightName) {
        this.limelightName = limelightName;
    }

    public double getTX() {
        return LimelightHelpers.getTX(limelightName);
    }

    public double getTY() {
        return LimelightHelpers.getTY(limelightName);
    }

    public double getTA() {
        return LimelightHelpers.getTA(limelightName);
    }

    // Adicione outros métodos conforme necessário para acessar dados da Limelight

    @Override
    public void periodic() {
        // Adicione código a ser executado periodicamente, se necessário
        SmartDashboard.putNumber("TA", LimelightHelpers.getTA(limelightName));
        SmartDashboard.putNumber("TX", LimelightHelpers.getTX(limelightName));
        SmartDashboard.putNumber("TY", LimelightHelpers.getTY(limelightName));
        SmartDashboard.putNumber("AprilTag", LimelightHelpers.getFiducialID(limelightName));

    }
}
