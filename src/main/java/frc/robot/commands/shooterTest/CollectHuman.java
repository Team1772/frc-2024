package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterTest;

public class CollectHuman extends Command {

    private ShooterTest shooter;

    private Intake intake;

    public CollectHuman(ShooterTest shooter, Intake intake) {
        this.shooter = shooter;
        this.intake = intake;
        addRequirements(this.shooter, this.intake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        this.shooter.set(-0.3, -0.3);
        this.intake.set(0.4);
    }

    @Override
    public void end(boolean interrupted) {
        this.shooter.stop();
        this.intake.stop();
    }
}