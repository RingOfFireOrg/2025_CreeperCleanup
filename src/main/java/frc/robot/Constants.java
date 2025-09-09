// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 * 
 * Key Components:
 * - Motor IDs: MOTOR_LEFT_1_ID, MOTOR_RIGHT_1_ID.
 * - Controller IDs: CONTROLLER_DRIVER_ID, CONTROLLER_MANIPULATOR_ID.
 * - Axis Mappings: LEFT_STICK_Y, RIGHT_STICK_Y.
 * - Subsystem-Specific Constants: HammerConstants, DriveConstants,
 * AutoConstants.
 */

public final class Constants {
    // Drive Motor IDs
    public static final int MOTOR_LEFT_1_ID = 0; // ID for left motor
    public static final int MOTOR_RIGHT_1_ID = 1; // ID for right motor

    // Controller IDs
    public static final int CONTROLLER_DRIVER_ID = 0; // Driver controller ID for drivetrain
    public static final int CONTROLLER_MANIPULATOR_ID = 1; // Manipulator controller ID for hammer

    // Xbox Controller Axes
    public static final int LEFT_STICK_Y = XboxController.Axis.kLeftY.value; // 1;
    public static final int RIGHT_STICK_Y = XboxController.Axis.kRightY.value; // 5

    // Add separate inner classes for subsystem-specific constants
    public static final class DriveConstants {
        public static final double SPEED_FACTOR = 0.86;
    }

    // Hammer constants
    public static final class HammerConstants {
        public static final int kMotorPWMPort = 5; // Robot's PWM port on RoboRio
        public static final double kMaxVoltage = 12.0;
        public static final double MAX_SPEED = 0.5;
        public static final double MIN_SPEED = -0.5;
        public static final int HammerCANID = 21; // CAN ID for the hammer motor controller
        public static final double VoltageFactor = 0.5; // Adjust this factor based on testing
    }

    public enum NeoPixelColors {
        BLUE,
        RED,
        GREEN
    }

    public static final NeoPixelColors TEAM_COLOR = NeoPixelColors.GREEN;
}
