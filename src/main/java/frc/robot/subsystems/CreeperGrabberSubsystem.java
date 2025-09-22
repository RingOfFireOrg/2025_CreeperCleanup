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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.GrabberInterface.CreeperInterfaceInputs;

public class CreeperGrabberSubsystem extends SubsystemBase {
    private final GrabberInterface io;
    private final CreeperInterfaceInputs inputs = new CreeperInterfaceInputs();

    public CreeperGrabberSubsystem(GrabberInterface io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        SmartDashboard.putNumber("Pivot/Voltage", inputs.appliedPivotVolts);
        SmartDashboard.putNumber("Wheels/Voltage", inputs.appliedWheelVolts);
    }

    public void runPivotVoltage(double volts) {
        io.setPivotVoltage(volts);
    }

    public void runWheelsVoltage(double volts) {
        io.setWheelsVoltage(volts);
    }

    public double getPosition() {
        return inputs.positionDeg;
    }

    public double getVelocity() {
        return inputs.pivotVelocityDegPerSec;
    }

    public void moveUp() {
        io.moveUp();
    }

    public void intake() {
        io.intakeWheels();
    }

    public void outtake() {
        io.outtakeWheels();
    }

    public void moveDown() {
        io.moveDown();
    }

    public void stopWheels() {
        io.stopWheels();
    }

    public void stopPivot() {
        io.stopPivot();
    }

    public void stop() {
        io.stop();
    }

    public void setSpeed(double speed) {
        io.setSpeed(speed);
    }

}