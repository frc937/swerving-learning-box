// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveFieldOriented;
import frc.robot.commands.DriveRobotOriented;
import frc.robot.commands.EnterXMode;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final Drive drive = new Drive();

  /*
   * ************
   * * COMMANDS *
   * ************
   */

  private final DriveRobotOriented driveRobotOriented = new DriveRobotOriented(drive);
  private final DriveFieldOriented driveFieldOriented = new DriveFieldOriented(drive);
  private final EnterXMode enterXMode = new EnterXMode(drive);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public static CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    drive.setDefaultCommand(driveRobotOriented);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    m_driverController.leftStick().toggleOnTrue(driveFieldOriented);

    m_driverController.x().onTrue(enterXMode);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }

  public static double scaleAxis(double axis) {
    double deadbanded = MathUtil.applyDeadband(axis, 0.1);
    return Math.pow(deadbanded, 3);
  }

  public static double getControllerLeftXAxis() {
    return m_driverController.getLeftX() * -1;
  }

  public static double getScaledControllerLeftXAxis() {
    return scaleAxis(getControllerLeftXAxis());
  }

  public static double getControllerLeftYAxis() {
    return m_driverController.getLeftY() * -1;
  }

  public static double getScaledControllerLeftYAxis() {
    return scaleAxis(getControllerLeftYAxis());
  }

  public static double getControllerRightXAxis() {
    return m_driverController.getRightX() * -1;
  }

  public static double getScaledControllerRightXAxis() {
    return scaleAxis(getControllerRightXAxis());
  }

  public static double getControllerRightYAxis() {
    return m_driverController.getRightY() * -1;
  }

  public static double getScaledControllerRightYAxis() {
    return scaleAxis(getControllerRightYAxis());
  }
}
