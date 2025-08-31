// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

 // This file has changes for
// - removing LEFT_2 and RIGHT_2 Motor ID
// - using enums for LEft and Right Stick
// It starts as base for the Creeper Cleaner robot code. 
// TODO: Convert to 2025 template and add Hammer and Sweeper constants
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  // Motor Controllers constants
  public static final int MOTOR_LEFT_1_ID = 0; // Left
  //public static final int MOTOR_LEFT_2_ID = 5;
  public static final int MOTOR_RIGHT_1_ID = 1; // Right
  //public static final int MOTOR_RIGHT_2_ID = 6;

  // Xbox Controllers ports
  public static final int CONTROLLER_DRIVER_ID = 0;
  public static final int CONTROLLER_MANIPULATOR_ID = 1;

  public static final int LEFT_STICK_X = XboxController.Axis.kLeftX.value; //0;
  public static final int LEFT_STICK_Y = XboxController.Axis.kLeftY.value; // 1;
  public static final int RIGHT_STICK_X = XboxController.Axis.kRightX.value; //4
  public static final int RIGHT_STICK_Y = XboxController.Axis.kRightY.value; //5

  public static final int DIO_RED_LEDS = 0;
  public static final int DIO_BLUE_LEDS = 1;

  // Hammer Constants
  public static final int kHammerMotorPort = 15;  // Adjust as needed
  public static final int kUpperLimitPort = 0;    // Adjust as needed
  public static final int kLowerLimitPort = 1;    // Adjust as needed

public static final int MOTOR_SWEEPER_ID = 5;

}
