// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class TankDriveCommand extends Command {
  /** Creates a new TankDriveCommand. */
  public TankDriveCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.myDriveTrain);
  }

  private double speedFactor = 0.58;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get the value from the user.. how the drive controller is pressed
    double LeftStickY = Robot.m_robotContainer.GetDriverRawAxis(Constants.LEFT_STICK_Y);
    double RightStickY = Robot.m_robotContainer.GetDriverRawAxis(Constants.RIGHT_STICK_Y);
    LeftStickY = LeftStickY * speedFactor;
    RightStickY = RightStickY * speedFactor;
    SmartDashboard.putNumber("LeftStickValue", LeftStickY);
    SmartDashboard.putNumber("RightStickValue", RightStickY);


    // This is the code that actually drives the robot... We are multiplying the speeds so that it grudually increases the speed
    if(LeftStickY <= 0)
    {
      Robot.myDriveTrain.setLeftMotors(-(LeftStickY*LeftStickY));
    }
    else{
      Robot.myDriveTrain.setLeftMotors(LeftStickY*LeftStickY);
    }
    
    if(RightStickY <= 0)
    {
      Robot.myDriveTrain.setRightMotors(-(RightStickY*RightStickY));
    }
    else{
      Robot.myDriveTrain.setRightMotors(RightStickY*RightStickY);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.myDriveTrain.setLeftMotors(0);
    Robot.myDriveTrain.setRightMotors(0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
