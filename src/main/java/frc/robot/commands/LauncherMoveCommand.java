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
 * Kevin was here.
 */

 package frc.robot.commands;

 import edu.wpi.first.wpilibj2.command.Command;
 import frc.robot.subsystems.LauncherSubSystem;
 
 public class LauncherMoveCommand extends Command {
     private final LauncherSubSystem launcher;
     private final double speed;
 
     public LauncherMoveCommand(LauncherSubSystem launcher, double speed) {
         this.launcher = launcher;
         this.speed = speed;
         addRequirements(launcher);
     }
 
     @Override
     public void initialize() {
        // hammer.hit();
     }
 
     @Override
     public void execute() {
         launcher.runVoltage(0.15 * speed);
     }
 
     @Override
     public void end(boolean interrupted) {
         launcher.runVoltage(0.0);
     }
 }
