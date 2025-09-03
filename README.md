README file for CreeperCleanup

This is the 2025 Sprint 1 code. This code will be used In Sprint 1a), starting 2025-09-04.

Main components: 
RobotContainer.java: 
* The RobotContainer class is the central hub for the robot's subsystems, commands, and input bindings.
 * It initializes and manages the robot's subsystems, commands, and operator interface (OI) devices.
 *
 * Key Responsibilities:
 * - Declare and initialize subsystems (e.g., DriveTrainSubSystem, HammerSubsystem).
 * - Set default commands for subsystems.
 * - Configure input bindings (e.g., joystick buttons and axes).
 * - Provide the autonomous command to the Robot class.
 *
 * Key Components:
 * - Subsystems: DriveTrainSubSystem, HammerSubsystem.
 * - Commands: TankDriveCommand, HammerMoveCommand.
 * - Input Devices: XboxController for driver and manipulator.
  
   Constants.java:

    * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 * 
 *  Key Components:
 * - Motor IDs: MOTOR_LEFT_1_ID, MOTOR_RIGHT_1_ID.
 * - Controller IDs: CONTROLLER_DRIVER_ID, CONTROLLER_MANIPULATOR_ID.
 * - Axis Mappings: LEFT_STICK_X, LEFT_STICK_Y, RIGHT_STICK_X, RIGHT_STICK_Y.
 * - Subsystem-Specific Constants: HammerConstants, DriveConstants, AutoConstants.
  
Subsystems: 
-- Hammer Subsystem: 
* The HammerSubsystem class represents the hammer mechanism of the robot.
 * It interacts with the hardware through the HammerInterface and provides methods for controlling the hammer.
 *
 * Key Responsibilities:
 * - Update and monitor hammer inputs (e.g., position, velocity, applied voltage).
 * - Provide methods to control the hammer (e.g., runVoltage, swingForward, swingBackward, stop).
 *
 * Key Components:
 * - Interface: HammerInterface for hardware abstraction.
 * - Inputs: HammerInterfaceInputs for storing sensor data.
 * - Methods: runVoltage, swingForward, swingBackward, stop.
 *
 * Lifecycle:
 * - `periodic()`: Called periodically by the WPILib scheduler to update inputs and send data to the dashboard.

DriveTrainSubsystem:
 * The DriveTrainSubSystem interface defines the contract for controlling the drivetrain.
 * It provides methods for setting the speed of the left and right motors.
 *
 * Key Responsibilities:
 * - Define methods for controlling the drivetrain motors (setLeftMotors, setRightMotors).
 *
 * Key Components:
 * - Methods: setLeftMotors, setRightMotors.
  
DriveInterface: 
 * The DriveInterface defines the contract for interacting with the drivetrain hardware.
 * It provides methods for controlling the left and right motors.
 *
 * Key Responsibilities:
 * - Define methods for controlling the drivetrain motors (setLeftMotors, setRightMotors).
 *
 * Key Components:
 * - Methods: setLeftMotors, setRightMotors.

HammerInterface:
* The HammerInterface defines the contract for interacting with the hammer hardware.
 * It provides methods for controlling the hammer and updating its inputs.
 *
 * Key Responsibilities:
 * - Define methods for controlling the hammer (e.g., setVoltage, swingForward, swingBackward, stop).
 * - Provide a structure for storing hammer inputs (HammerInterfaceInputs).
 *
 * Key Components:
 * - HammerInterfaceInputs: Stores position, velocity, applied voltage, and current.
 * - Default Methods: Provide default implementations for hardware control methods.
  
HammerinterfaceReal:
The HammerInterfaceReal implements the functions defined in the contract for interacting 
 * with the hammer hardware.
 * It provides methods for controlling the hammer and updating its inputs.
 *
 * Key Responsibilities:
 * - Implements methods for controlling the hammer (e.g., setVoltage, swingForward, swingBackward, stop).
 * - 
 * Key Components:
 * - HammerInterfaceInputs: Stores position, velocity, applied voltage, and current.
 * - Methods: setVoltage, swingForward, swingBackward, stop.
  
TankDriveCommand: 
The TankDriveCommand class controls the drivetrain subsystem using tank drive logic.
 * It is a command in the WPILib Command-based framework.
 *
 * Key Responsibilities:
 * - Read joystick input for left and right stick Y-axes.
 * - Apply squared inputs to the drivetrain motors for smoother control.
 *
 * Key Components:
 * - Subsystem: DriveTrainSubSystem.
 * - Input: XboxController for joystick values.
 *
 * Lifecycle:
 * - `execute()`: Called repeatedly to read joystick input and control the drivetrain.
 * - `end()`: Stops the drivetrain motors when the command ends.
 * - `isFinished()`: Always returns false to keep the command running.

HammerMoveCommand: 
* The HammerMoveCommand class controls the hammer subsystem by applying voltage based on joystick input.
 * It is a command in the WPILib Command-based framework.
 *
 * Key Responsibilities:
 * - Read joystick input (voltage supplier) and apply voltage to the hammer subsystem.
 * - Stop the hammer when the command ends.
 *
 * Key Components:
 * - Subsystem: HammerSubsystem.
 * - Input: DoubleSupplier for joystick voltage.
 *
 * Lifecycle:
 * - `initialize()`: Called once when the command starts.
 * - `execute()`: Called repeatedly while the command is active.
 * - `end()`: Called once when the command ends or is interrupted.

 HammerSwingForwardCommand and HammerSwingBackwardCommand are not implemented yet. 
