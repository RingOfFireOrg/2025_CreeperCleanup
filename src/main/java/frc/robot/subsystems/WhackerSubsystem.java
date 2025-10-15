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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.WhackerInterface.WhackerInterfaceInputs;

public class WhackerSubsystem extends SubsystemBase {
    private final WhackerInterface io;
    private final WhackerInterfaceInputs inputs = new WhackerInterfaceInputs();

    public WhackerSubsystem(WhackerInterface io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        SmartDashboard.putNumber("Whacker/Voltage", inputs.appliedVolts);
    }

    public void runVoltage(double volts) {
        io.setVoltage(volts);
    }

    public double getPosition() {
        return inputs.positionDeg;
    }

    public double getVelocity() {
        return inputs.velocityDegPerSec;
    }

    public void swingForward() {
        io.swingForward();
    }

    public void swingBackward() {
        io.swingBackward();
    }

    public void stop() {
        io.stop();
    }

    public void setSpeed(double speed) {
        io.setSpeed(speed);
    }

}