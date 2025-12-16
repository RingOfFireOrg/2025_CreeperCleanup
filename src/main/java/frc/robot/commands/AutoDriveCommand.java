/**
 * The HammerMoveCommand class controls the hammer subsystem by applying voltage based on joystick input.
 * It is a command in the WPILib Command-based framework.
 *
 * Key Responsibilities:
 * - Read joystick input (voltage supplier) and apply voltage to the hammer subsystem.
 * - Stop the hammer when the command ends.
 *
 * Key Components:
 * - Subsystem: HammerSubsystem.
 * - Input: DoubleSupplier for joystick voltage.
 *
 * Lifecycle:
 * - `initialize()`: Called once when the command starts.
 * - `execute()`: Called repeatedly while the command is active.
 * - `end()`: Called once when the command ends or is interrupted.
 */

 package frc.robot.commands;

 import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;
 
 public class AutoDriveCommand extends Command {
   private final DriveTrainSubSystem driveTrain;
   private final double leftspeed;
   private final double rightspeed;

 
   public AutoDriveCommand(DriveTrainSubSystem subsystem, double leftspeed, double rightspeed) {
    this.driveTrain = subsystem;
    this.leftspeed=leftspeed;
    this.rightspeed=rightspeed;

    addRequirements(subsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get the value from the user.. how the drive controller is pressed
    

    driveTrain.setLeftMotors(leftspeed);
    driveTrain.setRightMotors(rightspeed);
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