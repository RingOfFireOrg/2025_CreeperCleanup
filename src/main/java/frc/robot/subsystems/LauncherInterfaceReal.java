// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*** 
 * The HammerInterfaceReal implements the functions defined in the contract for interacting 
 * with the hammer hardware.
 * It provides methods for controlling the hammer and updating its inputs.
 *
 * Key Responsibilities:
 * - Implements methods for controlling the hammer (e.g., setVoltage, swingForward, swingBackward, stop).
 * - 
 * Key Components:
 * - HammerInterfaceInputs: Stores position, velocity, applied voltage, and current.
 * - Methods: setVoltage, swingForward, swingBackward, stop.
*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.Constants.LauncherConstants;;

public class LauncherInterfaceReal implements LauncherInterface {
    private final SparkMax motor;
    private SparkMaxConfig config = new SparkMaxConfig();
    public static final int LauncherCANID = LauncherConstants.LauncherCANID;;
    private double lastAppliedVolts = 0.0;

    public LauncherInterfaceReal() {
        config.idleMode(IdleMode.kBrake).smartCurrentLimit(25);
        motor = new SparkMax(LauncherCANID, MotorType.kBrushless);
        motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    @Override
    public void updateInputs(LauncherInterfaceInputs inputs) {
        inputs.appliedVolts = lastAppliedVolts;

        if (DriverStation.isDisabled()) {
            motor.setVoltage(0);
        }
    }

    @Override
    public void setVoltage(double volts) {
        lastAppliedVolts = volts;
        motor.setVoltage(volts * LauncherConstants.VoltageFactor);
    }

    @Override
    public void intake() {
        motor.set(0.2); //Half speed forward
    }

    public void outTake() {
        motor.set(-0.2); // Half speed reverse
    }

    public void setSpeed(double speed) {
        motor.set(speed);
    }

    public void stop() {
        motor.set(0);
    }

}