// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The HammerSubsystem class represents the hammer mechanism of the robot.
 * It interacts with the hardware through the HammerInterface and provides methods for controlling the hammer.
 *
 * Key Responsibilities:
 * - Update and monitor hammer inputs (e.g., position, velocity, applied voltage).
 * - Provide methods to control the hammer (e.g., runVoltage, swingForward, swingBackward, stop).
 *
 * Key Components:
 * - Interface: HammerInterface for hardware abstraction.
 * - Inputs: HammerInterfaceInputs for storing sensor data.
 * - Methods: runVoltage, swingForward, swingBackward, stop.
 *
 * Lifecycle:
 * - `periodic()`: Called periodically by the WPILib scheduler to update inputs and send data to the dashboard.
 */

 package frc.robot.subsystems;

 import com.revrobotics.spark.SparkMax;
 import com.revrobotics.spark.SparkBase.ResetMode;
 import com.revrobotics.spark.SparkBase.PersistMode;
 import com.revrobotics.spark.SparkLowLevel.MotorType;
 import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
 import com.revrobotics.spark.config.SparkMaxConfig;
 import edu.wpi.first.wpilibj2.command.SubsystemBase;
 import frc.robot.Constants.HammerConstants;

public class HammerSubsystem extends SubsystemBase {
    private final SparkMax arm; 

    public HammerSubsystem() {
        arm = new SparkMax(HammerConstants.HammerCANID, MotorType.kBrushless);
        
        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(IdleMode.kBrake)
              .smartCurrentLimit(25);
        
        arm.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }


    @Override
    public void periodic() {
     
    }

  
 
    public void stop() {
        arm.set(0.0);
    }

    public void setSpeed(double speed) {
        speed = Math.max(-1, Math.min(1,speed));
        arm.set(speed);
    }

}