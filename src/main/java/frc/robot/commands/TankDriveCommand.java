// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class TankDriveCommand extends Command {
    private final DriveTrainSubSystem driveTrain;
    private final XboxController controller;
   
    public TankDriveCommand(DriveTrainSubSystem subsystem, XboxController controller) {
        this.driveTrain = subsystem;
        this.controller = controller;
        addRequirements(subsystem);
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get the value from the user.. how the drive controller is pressed
    double LeftStickY = controller.getRawAxis(Constants.LEFT_STICK_Y) * DriveConstants.SPEED_FACTOR;
    double RightStickY = controller.getRawAxis(Constants.RIGHT_STICK_Y) * DriveConstants.SPEED_FACTOR;
   
    LeftStickY = LeftStickY * DriveConstants.SPEED_FACTOR;
    RightStickY = RightStickY * DriveConstants.SPEED_FACTOR;
    SmartDashboard.putNumber("LeftStickValue", LeftStickY);
    SmartDashboard.putNumber("RightStickValue", RightStickY);


    // This is the code that actually drives the robot... We are multiplying the speeds so that it gradually increases the speed
    if(LeftStickY <= 0)
    {
      driveTrain.setLeftMotors(-(LeftStickY*LeftStickY));
    }
    else{
      driveTrain.setLeftMotors(LeftStickY*LeftStickY);
    }
    
    if(RightStickY <= 0)
    {
      driveTrain.setRightMotors(-(RightStickY*RightStickY));
    }
    else{
      driveTrain.setRightMotors(RightStickY*RightStickY);
    }
    
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