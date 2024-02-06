package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.ShooterTest;

public class CollectHuman extends Command {

    private ShooterTest shooter;

    private Intake intake;

    private IntakeMove intakeMove;

    public CollectHuman(ShooterTest shooter, Intake intake, IntakeMove intakeMove) {
        this.shooter = shooter;
        this.intake = intake;
        this.intakeMove = intakeMove;
        addRequirements(this.shooter, this.intake, this.intakeMove);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (intakeMove.isLimitMax()) {
            this.shooter.set(-0.2);
            this.intake.set(-0.45);
        }else{
            this.intakeMove.set(0.9);
        }

    }

    @Override
    public void end(boolean interrupted) {
        this.shooter.stop();
        this.intake.stop();
        this.intakeMove.stop();
    }
}