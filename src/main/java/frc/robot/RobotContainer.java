// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }
 

  // XBox Controllers constants
  private XboxController driverController = new XboxController(Constants.CONTROLLER_DRIVER_ID);
  private XboxController ManipulatorController = new XboxController(Constants.CONTROLLER_MANIPULATOR_ID);

  public double GetDriverRawAxis(int axis)
  {
      return driverController.getRawAxis(axis);
  }

  public double GetManipulatorRawAxis(int axis)
  {
    return ManipulatorController.getRawAxis(axis);
  }

  // this needs a real implementation using a DIO on switch - rmackie
  public boolean getSweeperLimitSwitchValue()
  {
    return false;
  }

  private void configureBindings() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

}
