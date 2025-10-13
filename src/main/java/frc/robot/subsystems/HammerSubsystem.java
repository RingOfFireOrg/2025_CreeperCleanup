// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The HammerSubsystem class represents the hammer mechanism of the robot.
 * It controls a SparkMax motor controller for swinging the hammer mechanism.
 *
 * Key Responsibilities:
 * - Control the hammer motor using speed control
 * - Provide methods to swing forward, backward, and stop
 * - Monitor motor telemetry
 *
 * Key Components:
 * - SparkMax motor controller configured in brake mode
 * - Speed control methods
 * - SmartDashboard telemetry
 */

 package frc.robot.subsystems;

 import com.revrobotics.spark.SparkMax;
 import com.revrobotics.spark.SparkBase.ResetMode;
 import com.revrobotics.spark.SparkBase.PersistMode;
 import com.revrobotics.spark.SparkLowLevel.MotorType;
 import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
 import com.revrobotics.spark.config.SparkMaxConfig;
 import edu.wpi.first.wpilibj2.command.SubsystemBase;
 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 import frc.robot.Constants.HammerConstants;
 
 public class HammerSubsystem extends SubsystemBase {
     private final SparkMax motor;
 
     public HammerSubsystem() {
         motor = new SparkMax(HammerConstants.HammerCANID, MotorType.kBrushless);
         
         SparkMaxConfig config = new SparkMaxConfig();
         config.idleMode(IdleMode.kBrake)
               .smartCurrentLimit(25);
         
         motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
     }
 
     @Override
     public void periodic() {
         // Update telemetry
         SmartDashboard.putNumber("Hammer/Motor Speed", motor.get());
         SmartDashboard.putNumber("Hammer/Motor Current", motor.getOutputCurrent());
         SmartDashboard.putNumber("Hammer/Motor Temperature", motor.getMotorTemperature());
     }
 
     /**
      * Set the hammer motor speed directly
      * @param speed Motor speed (-1.0 to 1.0)
      */
     public void setSpeed(double speed) {
         // Clamp speed to valid range
         speed = Math.max(-1.0, Math.min(1.0, speed));
         motor.set(speed);
         SmartDashboard.putNumber("encoder location", motor.getEncoder().getPosition());
     }
     
     public void encoderReset() {
         motor.getEncoder().setPosition(0);
     }
        
 
     /**
      * Stop the hammer motor
      */
     public void stop() {
        SmartDashboard.putString("Hammer Status", "Stopping");
        while ((motor.getEncoder().getPosition()%1) < 1 || (motor.getEncoder().getPosition()%1) > -1) {
            motor.set(0.05);
            SmartDashboard.putNumber("encoder location", motor.getEncoder().getPosition());
        }
         motor.set(0);
     }
 
     /**
      * Get the current motor speed
      * @return Current motor speed (-1.0 to 1.0)
      */
     public double getSpeed() {
         return motor.get();
     }
 }