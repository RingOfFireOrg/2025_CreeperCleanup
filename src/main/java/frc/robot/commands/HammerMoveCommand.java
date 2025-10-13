/**
 * The HammerMoveCommand class controls the hammer subsystem by applying speed based on joystick input.
 * It is a command in the WPILib Command-based framework.
 *
 * Key Responsibilities:
 * - Read joystick input (speed supplier) and apply speed to the hammer subsystem.
 * - Stop the hammer when the command ends.
 *
 * Key Components:
 * - Subsystem: HammerSubsystem.
 * - Input: DoubleSupplier for joystick speed.
 *
 * Lifecycle:
 * - `initialize()`: Called once when the command starts.
 * - `execute()`: Called repeatedly while the command is active.
 * - `end()`: Called once when the command ends or is interrupted.
 */

 package frc.robot.commands;

 import edu.wpi.first.wpilibj2.command.Command;
 import frc.robot.subsystems.HammerSubsystem;
 import java.util.function.DoubleSupplier;
 
 public class HammerMoveCommand extends Command {
     private final HammerSubsystem hammer;
     private final DoubleSupplier speedSupplier;
 
     public HammerMoveCommand(HammerSubsystem hammer, DoubleSupplier speedSupplier) {
         this.hammer = hammer;
         this.speedSupplier = speedSupplier;
         addRequirements(hammer);
     }
 
     @Override
     public void initialize() {
         // Called when command starts
         hammer.encoderReset();
     }
 
     @Override
     public void execute() {
         // Apply speed with scaling factor (15% of input)
         hammer.setSpeed(0.25 * speedSupplier.getAsDouble());
     }
 
     @Override
     public void end(boolean interrupted) {
         hammer.stop();
     }
 }