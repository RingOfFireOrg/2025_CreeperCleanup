// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.WhackerSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Auto extends Command {
  /** Creates a new Auto. */
  private final DriveTrainSubSystem driveTrain;
  private final WhackerSubsystem whacker;
  private long start_time;
  public Auto(DriveTrainSubSystem drivetrain, WhackerSubsystem whacker) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain, whacker);
    driveTrain = drivetrain;
    this.whacker = whacker;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    start_time = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     long now = System.currentTimeMillis();
     long difference = now - start_time;
     if (difference > 500 /* milliseconds */) {
      driveTrain.setLeftMotors(0);
      driveTrain.setRightMotors(0);
     } else {
      driveTrain.setLeftMotors(0.5);
      driveTrain.setRightMotors(0.5);
     }
     if (difference > 1000) {
      whacker.stop();
     } else if (difference > 9500) {
      whacker.swingBackward();
     } else if (difference > 9000) {
      whacker.swingForward();
     } else if (difference > 8500) {
      whacker.swingBackward();
     } else if (difference > 8000) {
      whacker.swingForward();
     } else if (difference > 7500) {
      whacker.swingBackward();
     } else if (difference > 7000) {
      whacker.swingForward();
     } else if (difference > 6500) {
      whacker.swingBackward();
     } else if (difference > 6000) {
      whacker.swingForward();
     } else if (difference > 5500) {
      whacker.swingBackward();
     } else if (difference > 5000) {
      whacker.swingForward();
     } else if (difference > 4500) {
      whacker.swingBackward();
     } else if (difference > 4000) {
      whacker.swingForward();
     } else if (difference > 3500) {
      whacker.swingBackward();
     } else if (difference > 3000) {
      whacker.swingForward();
     } else if (difference > 2500) {
      whacker.swingBackward();
     } else if (difference > 2000) {
      whacker.swingForward();
     } else if (difference > 1500) {
      whacker.swingBackward();
     } else if (difference > 1000) {
      whacker.swingForward();
     } else if (difference > 500) {
      whacker.swingBackward();
     } else {
      whacker.swingForward();
     } 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
