// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.core.util.Led;
import frc.robot.buttonBindings.OperatorButtonBindings;
import frc.robot.commands.shooter.ShootOff;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private boolean isDebbuging;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    isDebbuging = true;

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // m_robotContainer.led().rainbow();

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {
    if (isDebbuging) {
      if (m_robotContainer.isInfraredIntake()) {
        m_robotContainer.led().rgb(100, 100, 0);
      } else if (m_robotContainer.isLimitMinIntake()) {
        m_robotContainer.led().rgb(100, 0, 100);
      } else if (m_robotContainer.isLimitMaxIntake()) {
        m_robotContainer.led().rgb(0, 100, 100);
      } else if (m_robotContainer.isLimitMinClimber()) {
        m_robotContainer.led().rgb(255, 255, 255);

      } else {
        m_robotContainer.led().rgb(0, 0, 0);

      }
    } else {
      // m_robotContainer.led().rgb(255, 30, 0);
      m_robotContainer.led().rainbow();

    }
    ;
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (m_robotContainer.isInfraredIntake()) {
      m_robotContainer.led().rgb(0, 255, 0);
    } else {
      // m_robotContainer.led().rainbow();
      m_robotContainer.led().rgb(255, 0, 0);
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().schedule(new ShootOff(m_robotContainer.shooter));
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    switch (Led.identifier) {
      // AMP
      case 0:
        m_robotContainer.led().rgb(0, 0, 255);
        break;
      // SPEAKER
      case 1:
        m_robotContainer.led().rgb(120, 20, 0);
        break;
      case 2:
        m_robotContainer.led().rgb(255, 0, 255);
        break;
      case 3:
        m_robotContainer.led().rgb(0, 0, 255);
        break;
      // infraredOn
      case 4:
        m_robotContainer.led().rgb(0, 255, 0);
        break;
      // infraredOFF
      case 5:
        m_robotContainer.led().rgb(255, 0, 0);
        break;
    }

    if (RobotContainer.atSettedVelocity()) {
      OperatorButtonBindings.operator.enableRumble();
    } else {
      OperatorButtonBindings.operator.disableRumble();
    }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
