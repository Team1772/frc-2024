package frc.robot.commands.operator_rumble;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.OperatorRumble;

public class rumbleCollected extends Command {
    private OperatorRumble operatorRumble;

    public rumbleCollected(OperatorRumble operatorRumble) {
        this.operatorRumble = operatorRumble;
        addRequirements(this.operatorRumble);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        this.operatorRumble.rumble();
    }

    @Override
    public void end(boolean interrupted) {

    }
}
