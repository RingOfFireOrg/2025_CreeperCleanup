// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The DriveInterface defines the contract for interacting with the drivetrain hardware.
 * It provides methods for controlling the left and right motors.
 *
 * Key Responsibilities:
 * - Define methods for controlling the drivetrain motors (setLeftMotors, setRightMotors).
 *
 * Key Components:
 * - Methods: setLeftMotors, setRightMotors.
 */

 package frc.robot.subsystems;

public interface DriveInterface {
    void setLeftMotors(double speed);
    void setRightMotors(double speed);
}