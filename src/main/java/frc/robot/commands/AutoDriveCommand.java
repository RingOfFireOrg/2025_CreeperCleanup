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
  private final double leftSpeed;
  private final double rightSpeed;

  public AutoDriveCommand(DriveTrainSubSystem subsystem, double leftSpeed, double rightSpeed) {
    this.driveTrain = subsystem;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    addRequirements(subsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.setLeftMotors(leftSpeed);
    driveTrain.setRightMotors(rightSpeed);
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