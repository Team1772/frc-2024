package frc.robot.commands.shooterTest;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.intake.ReleaseTimer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterTest;

public class SmartShooterTimer extends ParallelCommandGroup {
  private Intake intake;
  private ShooterTest shooter;

  private double secondsEnabled;
  private double secondsEnabledUntilRelease;

  public SmartShooterTimer(double secondsEnabled, double secondsEnabledUntilRelease, Intake intake, ShooterTest shooter) {
    this.intake = intake;
    this.secondsEnabled = secondsEnabled;
    this.secondsEnabledUntilRelease = secondsEnabledUntilRelease;

    super.addCommands(
      new ShootTimer(shooter, 0.23, 0.23, this.secondsEnabled),
      new ReleaseTimer(intake, this.secondsEnabled, this.secondsEnabledUntilRelease)
    );

    super.addRequirements(this.intake, this.shooter);
  }
}