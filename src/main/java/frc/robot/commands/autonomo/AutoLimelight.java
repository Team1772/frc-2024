package frc.robot.commands.autonomo;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class AutoLimelight extends Command {
    private final Limelight limelightSubsystem;
    private final Drivetrain drivetrain;
    private final PIDController pidController = new PIDController(0.3, 0.01, 0.1); // Ajuste os valores P, I e D                                                                              // conforme
    // necessário
    private double tX;

    public AutoLimelight(Limelight limelightSubsystem, Drivetrain drivetrain) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrain = drivetrain;
        addRequirements(limelightSubsystem);
    }

    @Override
    public void initialize() {
        pidController.setTolerance(1); // Ajuste a tolerância conforme necessário
        pidController.setSetpoint(0); // Inicialize o setpoint do PID
    }

    @Override
    public void execute() {
        tX = limelightSubsystem.getTX();

        // Use o PID para calcular a saída com base no erro
        double output = pidController.calculate(tX);

        // Ajuste a lógica de controle da direção das rodas conforme necessário
        drivetrain.arcadeDrive(0.5, output);

        SmartDashboard.putNumber("TX AUTO", tX);
    }
}
