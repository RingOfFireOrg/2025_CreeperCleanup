// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;

public class AutodriveCommand extends Command {
  private final DriveTrainSubSystem driveTrain;
  private final double speed;


  public AutodriveCommand(DriveTrainSubSystem subsystem, double speed) {
    this.driveTrain = subsystem;
    this.speed = speed;
    addRequirements(subsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get the value from the user.. how the drive controller is pressed
   
    driveTrain.setLeftMotors(speed);
    driveTrain.setRightMotors(speed);
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