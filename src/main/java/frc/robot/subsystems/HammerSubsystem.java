// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The HammerSubsystem class represents the hammer mechanism of the robot.
 * It controls a SparkMax motor controller for swinging the hammer mechanism.
 *
 * Key Responsibilities:
 * - Control the hammer motor using voltage or speed control
 * - Provide methods to swing forward, backward, and stop
 * - Monitor motor telemetry
 *
 * Key Components:
 * - SparkMax motor controller configured in brake mode
 * - Voltage and speed control methods
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
     private double lastAppliedVolts = 0.0;
 
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
         SmartDashboard.putNumber("Hammer/Applied Voltage", lastAppliedVolts);
         SmartDashboard.putNumber("Hammer/Motor Current", motor.getOutputCurrent());
         SmartDashboard.putNumber("Hammer/Motor Temperature", motor.getMotorTemperature());
     }
 
     /**
      * Run the hammer at a specified voltage
      * @param volts Voltage to apply (-12 to 12)
      */
     public void runVoltage(double volts) {
         lastAppliedVolts = volts;
         motor.setVoltage(volts * HammerConstants.VoltageFactor);
     }
 
     /**
      * Set the hammer motor speed directly
      * @param speed Motor speed (-1.0 to 1.0)
      */
     public void setSpeed(double speed) {
         motor.set(speed);
     }
 
     /**
      * Stop the hammer motor
      */
     public void stop() {
         motor.set(0);
         lastAppliedVolts = 0.0;
     }
 }