package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeMoveConstants;
import frc.robot.constants.ShooterTestConstants;
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
            this.shooter.set(ShooterTestConstants.Speed.speedCollectShooterHumanPlayer);
            this.intake.set(ShooterTestConstants.Speed.speedCollectIntakeHumanPlayer);
        }else{
            this.intakeMove.set(IntakeMoveConstants.Speed.speedUp);
        }

    }

    @Override
    public void end(boolean interrupted) {
        this.shooter.stop();
        this.intake.stop();
        this.intakeMove.stop();
    }
}