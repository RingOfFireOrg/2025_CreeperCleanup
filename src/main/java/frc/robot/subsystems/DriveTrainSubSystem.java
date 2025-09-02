// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The DriveTrainSubSystem interface defines the contract for controlling the drivetrain.
 * It provides methods for setting the speed of the left and right motors.
 *
 * Key Responsibilities:
 * - Define methods for controlling the drivetrain motors (setLeftMotors, setRightMotors).
 *
 * Key Components:
 * - Methods: setLeftMotors, setRightMotors.
 */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//import frc.robot.commands.TankDriveCommand;

public class DriveTrainSubSystem extends SubsystemBase implements DriveInterface {
    private final VictorSP motorLeft1;
    private final VictorSP motorRight1;

    public DriveTrainSubSystem() {
        motorLeft1 = new VictorSP(Constants.MOTOR_LEFT_1_ID);
        motorRight1 = new VictorSP(Constants.MOTOR_RIGHT_1_ID);
        motorRight1.setInverted(true);
        //setDefaultCommand(new TankDriveCommand(this));
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void setLeftMotors(double speed) {
        motorLeft1.set(-speed);
    }

    @Override
    public void setRightMotors(double speed) {
        motorRight1.set(speed);
    }
}
