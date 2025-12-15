// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The TankDriveCommand class controls the drivetrain subsystem using tank drive logic.
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
 */

 package frc.robot.commands;

 import edu.wpi.first.wpilibj2.command.Command;

 import frc.robot.subsystems.DriveTrainSubSystem;

 
 public class AutoDriveCommand extends Command {
   private final DriveTrainSubSystem driveTrain;
   private final double leftspeed;
   private final double rightspeed;

 
   public AutoDriveCommand(DriveTrainSubSystem subsystem, double leftspeed, double rightspeed) {
    this.driveTrain = subsystem;
    this.leftspeed=leftspeed;
    this.rightspeed=rightspeed;

    addRequirements(subsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get the value from the user.. how the drive controller is pressed
    

    driveTrain.setLeftMotors(leftspeed);
    driveTrain.setRightMotors(rightspeed);
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }


}