// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.HammerMoveCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.HammerIOReal;
import frc.robot.subsystems.HammerSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final XboxController driverController = new XboxController(0);
    private final DriveTrainSubSystem driveTrain = new DriveTrainSubSystem();
    private final HammerSubsystem m_hammer = new HammerSubsystem(new HammerIOReal(Constants.HammerConstants.kMotorPWMPort));


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        configureBindings(); // TODO is it needed? or called by wpilib? 
        configureButtonBindings(); // TODO same here?       
        driveTrain.setDefaultCommand(
            new TankDriveCommand(driveTrain, driverController)
        );
        
    }

    private void configureBindings() {
        // Example: Run hammer with joystick
        m_hammer.setDefaultCommand(
            new HammerMoveCommand (
                m_hammer,
                () -> -driverController.getRawAxis(1) * Constants.HammerConstants.kMaxVoltage
            )
        );
    }
    // XBox Controllers constants
    private XboxController ManipulatorController = new XboxController(Constants.CONTROLLER_MANIPULATOR_ID);

    public double GetDriverRawAxis(int axis) {
        return driverController.getRawAxis(axis);
    }

    public double GetManipulatorRawAxis(int axis) {
        return ManipulatorController.getRawAxis(axis);
    }

    // this needs a real implementation using a DIO on switch - rmackie
    public boolean getSweeperLimitSwitchValue() {
        return false;
    }

    private void configureButtonBindings() {
        // Configure button bindings here
    }

    public Command getAutonomousCommand() {
    // TODO: Add actual autonomous command when needed
    return null;
}

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
}
