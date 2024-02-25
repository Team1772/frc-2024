package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.Led;
import frc.robot.constants.IntakeMoveConstants;
import frc.robot.constants.ShooterTestConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMove;
import frc.robot.subsystems.Shooter;

public class CollectHuman extends Command {

    private Shooter shooter;

    private Intake intake;

    private IntakeMove intakeMove;

    public CollectHuman(Shooter shooter, Intake intake, IntakeMove intakeMove) {
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
            this.shooter.setPercentOutput(ShooterTestConstants.Speed.speedCollectShooterHumanPlayer);
            this.intake.set(ShooterTestConstants.Speed.speedCollectIntakeHumanPlayer);
        }else{
            this.intakeMove.set(IntakeMoveConstants.Speed.speedUp);
        }
        Led.identifier = 2;

    }

    @Override
    public void end(boolean interrupted) {
        this.shooter.stop();
        this.intake.stop();
        this.intakeMove.stop();
    }
}