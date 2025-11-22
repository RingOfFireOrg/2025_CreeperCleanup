// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The DriveTrainSubSystem controls the robot's tank drive system.
 * It manages two VictorSP motor controllers for left and right sides.
 *
 * Key Responsibilities:
 * - Control left and right drivetrain motors
 * - Provide tank drive and arcade drive methods
 * - Apply deadband to joystick inputs
 *
 * Key Components:
 * - Two VictorSP motor controllers (one per side)
 * - Tank drive control methods
 */

 package frc.robot.subsystems;

 import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
 import edu.wpi.first.wpilibj2.command.SubsystemBase;
 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 import frc.robot.Constants;
 
 public class DriveTrainSubSystem extends SubsystemBase {
     private final VictorSP motorLeft;
     private final VictorSP motorRight;
     
     private static final double DEADBAND = 0.05;
 
     public DriveTrainSubSystem() {
         motorLeft = new VictorSP(Constants.MOTOR_LEFT_1_ID);
         motorRight = new VictorSP(Constants.MOTOR_RIGHT_1_ID);
         
         // Invert right side for correct tank drive behavior
         motorRight.setInverted(true);
     }
 
     @Override
     public void periodic() {
         // Update telemetry
         SmartDashboard.putNumber("Drive/Left Speed", motorLeft.get());
         SmartDashboard.putNumber("Drive/Right Speed", motorRight.get());
     }
 
     /**
      * Set the speed of the left motors
      * @param speed Motor speed (-1.0 to 1.0)
      */
     public void setLeftMotors(double speed) {
         speed = applyDeadband(speed);
         motorLeft.set(-speed); // Negated for correct direction
     }
 
     /**
      * Set the speed of the right motors
      * @param speed Motor speed (-1.0 to 1.0)
      */
     public void setRightMotors(double speed) {
         speed = applyDeadband(speed);
         motorRight.set(speed);
     }
 
     /**
      * Tank drive method for differential drive
      * @param leftSpeed Speed for left side (-1.0 to 1.0)
      * @param rightSpeed Speed for right side (-1.0 to 1.0)
      */
     public void tankDrive(double leftSpeed, double rightSpeed) {
         setLeftMotors(leftSpeed);
         setRightMotors(rightSpeed);
     }
 
     /**
      * Arcade drive method (alternative control scheme)
      * @param speed Forward/backward speed (-1.0 to 1.0)
      * @param rotation Turn rate (-1.0 to 1.0)
      */
     public void arcadeDrive(double speed, double rotation) {
         speed = applyDeadband(speed);
         rotation = applyDeadband(rotation);
 
         double leftSpeed = speed + rotation;
         double rightSpeed = speed - rotation;
 
         // Normalize wheel speeds
         double maxMagnitude = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
         if (maxMagnitude > 1.0) {
             leftSpeed /= maxMagnitude;
             rightSpeed /= maxMagnitude;
         }
 
         setLeftMotors(leftSpeed);
         setRightMotors(rightSpeed);
     }
 
     /**
      * Stop all drive motors
      */
     public void stop() {
         motorLeft.set(0);
         motorRight.set(0);
     }
 
     /**
      * Apply deadband to joystick input
      * @param value Input value
      * @return Processed value with deadband applied
      */
     private double applyDeadband(double value) {
         if (Math.abs(value) < DEADBAND) {
             return 0.0;
         }
         return value;
     }
 }