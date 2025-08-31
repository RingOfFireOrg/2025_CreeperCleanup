// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
    // Drive Motor IDs
    public static final int MOTOR_LEFT_1_ID = 0;
    public static final int MOTOR_RIGHT_1_ID = 1;

    // Controller IDs
    public static final int CONTROLLER_DRIVER_ID = 0;
    public static final int CONTROLLER_MANIPULATOR_ID = 1;

    // Xbox Controller Axes
    public static final int LEFT_STICK_Y = 1;
    public static final int RIGHT_STICK_Y = 5;

    // Sweeper IDs
    public static final int SWEEPER_MOTOR_ID = 2;
    public static final int SWEEPER_LIMIT_SWITCH_ID = 0;

    // Add separate inner classes for subsystem-specific constants
    public static final class DriveConstants {
        public static final double SPEED_FACTOR = 0.58;
    }

    // Add autonomous constants
    public static final class AutoConstants {
        public static final double AUTO_SPEED = 0.5;
        public static final double AUTO_ROTATION = 0.5;
    }

    // Add sweeper constants
    public static final class SweeperConstants {
        public static final double MAX_SPEED = 0.8;
        public static final double MIN_SPEED = -0.8;
    }
}
