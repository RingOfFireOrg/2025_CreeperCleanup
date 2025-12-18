// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The RobotContainer class is the central hub for the robot's subsystems, commands, and input bindings.
 * It initializes and manages the robot's subsystems, commands, and operator interface (OI) devices.
 *
 * Key Responsibilities:
 * - Declare and initialize subsystems (e.g., DriveTrainSubSystem, HammerSubsystem, ClamperSubsystem).
 * - Set default commands for subsystems.
 * - Configure input bindings (e.g., joystick buttons and axes).
 * - Provide the autonomous command to the Robot class.
 *
 * Key Components:
 * - Subsystems: DriveTrainSubSystem, HammerSubsystem, ClamperSubsystem.
 * - Commands: TankDriveCommand, HammerMoveCommand, RotateClamperCommand.
 * - Input Devices: XboxController for driver and manipulator.
 */

 package frc.robot;

 import edu.wpi.first.wpilibj.DigitalOutput;
 import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
 //import frc.robot.Constants.HammerConstants;
 import frc.robot.commands.*;
 import frc.robot.subsystems.*;

 
 /**
  * This class is where the bulk of the robot should be declared. Since Command-based is a
  * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
  * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
  * subsystems, commands, and trigger mappings) should be declared here.
  */
 public class RobotContainer {
     private final XboxController driverController = new XboxController(Constants.CONTROLLER_DRIVER_ID);
     private final XboxController manipulatorController = new XboxController(Constants.CONTROLLER_MANIPULATOR_ID);
 
     private final DriveTrainSubSystem driveTrain = new DriveTrainSubSystem();
     private final ArmSubsystem arm = new ArmSubsystem();
     private final IntakeSubsystem intake = new IntakeSubsystem();
     private final TappingArmSubsystem tapper = new TappingArmSubsystem();
 
     private final DigitalOutput dioPin0 = new DigitalOutput(0);
     private final DigitalOutput dioPin1 = new DigitalOutput(1);
 
     /** The container for the robot. Contains subsystems, OI devices, and commands. */
     public RobotContainer() {
         setNeoPixelColor();
         configureBindings();
         driveTrain.setDefaultCommand(
                 new TankDriveCommand(driveTrain, driverController));
         }
 
     private void configureBindings() {
         // Hammer control with triggers: right trigger = forward, left trigger = backward
         arm.setDefaultCommand(
            new ArmMoveCommand(
                    arm,
                    () -> {
                        double triggerInput = -manipulatorController.getLeftY();
                        return triggerInput; // Returns -1.0 to 1.0, scaled in command
                    }));
        intake.setDefaultCommand(
            new IntakeCommand(
                    intake,
                    () -> {
                        double triggerInput = -manipulatorController.getRightTriggerAxis()+
                                              manipulatorController.getLeftTriggerAxis();
                        return triggerInput; // Returns -1.0 to 1.0, scaled in command
                    }));
        
        tapper.setDefaultCommand(
            new TappingArmCommand(
                    tapper,
                    () -> {
                        double triggerInput = -manipulatorController.getRightY();
                        return triggerInput; // Returns -1.0 to 1.0, scaled in command
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
 
     public double getDriverRawAxis(int axis) {
         return driverController.getRawAxis(axis);
     }
 
     public double getManipulatorRawAxis(int axis) {
         return manipulatorController.getRawAxis(axis);
     }
 
     
     public Command getAutonomousCommand() {
      return new Autonomous(driveTrain);
     }
