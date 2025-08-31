// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.Constants;

public class TankDriveCommand extends Command {
    private final DriveTrainSubSystem driveTrain;
    private final XboxController controller;
    private final double speedFactor = 0.58;

    public TankDriveCommand(DriveTrainSubSystem subsystem, XboxController controller) {
        this.driveTrain = subsystem;
        this.controller = controller;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        double leftStickY = controller.getRawAxis(Constants.LEFT_STICK_Y) * speedFactor;
        double rightStickY = controller.getRawAxis(Constants.RIGHT_STICK_Y) * speedFactor;
        
        SmartDashboard.putNumber("LeftStickValue", leftStickY);
        SmartDashboard.putNumber("RightStickValue", rightStickY);

        driveTrain.setLeftMotors(Math.copySign(leftStickY * leftStickY, leftStickY));
        driveTrain.setRightMotors(Math.copySign(rightStickY * rightStickY, rightStickY));
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.setLeftMotors(0);
        driveTrain.setRightMotors(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
