package frc.robot.commands; 

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Limelight;

public class LimelightCommand extends Command {
    private final Limelight limelightSubsystem;

    public LimelightCommand(Limelight limelightSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(limelightSubsystem);
    }

    @Override
    public void initialize() {
        // Código de inicialização, se necessário
    }

    @Override
    public void execute() {
        double tx = limelightSubsystem.getTX();
        double ty = limelightSubsystem.getTY();
        double ta = limelightSubsystem.getTA();

        // Faça algo com os valores obtidos, por exemplo, imprimir no console
        System.out.println("Limelight TX: " + tx + ", TY: " + ty + ", TA: " + ta);
    }

    @Override
    public void end(boolean interrupted) {
        // Código para encerrar o comando, se necessário
    }

    @Override
    public boolean isFinished() {
        return false; // Mantém o comando em execução continuamente; ajuste conforme necessário
    }
}
