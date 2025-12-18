// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
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
 */

package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.HammerMoveCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.HammerInterfaceReal;
import frc.robot.subsystems.HammerSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final XboxController driverController = new XboxController(Constants.CONTROLLER_DRIVER_ID);
    private final XboxController ManipulatorController = new XboxController(Constants.CONTROLLER_MANIPULATOR_ID);

    private final DriveTrainSubSystem driveTrain = new DriveTrainSubSystem();
    private final HammerSubsystem hammer = new HammerSubsystem(
            new HammerInterfaceReal(Constants.HammerConstants.kMotorPWMPort));

    private final DigitalOutput dioPin0 = new DigitalOutput(0);
    private final DigitalOutput dioPin1 = new DigitalOutput(1);

    /** The container for the robot. Contains subsystems, OI devices, and commands.  */
    public RobotContainer() {
        setNeoPixelColor();
        configureBindings();
        driveTrain.setDefaultCommand(
                new TankDriveCommand(driveTrain, driverController));
    }

    private void configureBindings() {
        // Example: Run hammer with joystick
        hammer.setDefaultCommand(
                new HammerMoveCommand(
                        hammer,
                        () -> {
                            // {0to1} - {0to1} = {-1to1}
                            double xAxis = ManipulatorController.getRightTriggerAxis()
                                    - ManipulatorController.getLeftTriggerAxis();
                            return xAxis * Constants.HammerConstants.kMaxVoltage;
                        }));
    }

    /** Set color for NeoPixels */
    private void setNeoPixelColor() {

        switch (Constants.TEAM_COLOR) {
            case BLUE:
                dioPin0.set(false);
                dioPin1.set(true);
                break;
            case RED:
                dioPin0.set(true);
                dioPin1.set(false);
                break;
            case GREEN:
                dioPin0.set(true);
                dioPin1.set(true);
                break;
        }
    }

    public double GetDriverRawAxis(int axis) {
        return driverController.getRawAxis(axis);
    }

    public double GetManipulatorRawAxis(int axis) {
        return ManipulatorController.getRawAxis(axis);
    }

    public Command getAutonomousCommand() {
        return new Autonomous(driveTrain);
    }
}
