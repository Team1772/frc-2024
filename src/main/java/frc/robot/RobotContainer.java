package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.core.util.oi.SmartController;
import frc.robot.constants.OIConstants;
import frc.robot.commands.SwerveDrive;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.SwerveTurn;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

        private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

        private final XboxController driverJoytick = new XboxController(OIConstants.kDriverControllerPort);
        private final SmartController driver = new SmartController(0);
        public RobotContainer() {
                swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                                swerveSubsystem,
                                () -> driver.getLeftY(),
                                () -> - driver.getLeftX(),
                                () -> driver.getRightX(),
                                () -> !driver.getAButton().getAsBoolean()));

                configureButtonBindings();
        }

        private void configureButtonBindings() {

                driver.whileRightBumper(new SwerveTurn(swerveSubsystem));
                driver.whileLeftBumper(new SwerveDrive(swerveSubsystem));
        }

        public Command getAutonomousCommand() {
                return null;
        }
}
