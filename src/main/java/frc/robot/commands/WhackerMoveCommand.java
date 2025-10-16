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
import frc.robot.Constants;
import frc.robot.subsystems.WhackerSubsystem;
import java.util.function.DoubleSupplier;

public class WhackerMoveCommand extends Command {
    private final WhackerSubsystem whacker;
    private final DoubleSupplier voltageSupplier;
    public WhackerMoveCommand(WhackerSubsystem whacker, DoubleSupplier voltageSupplier) {
        this.whacker = whacker;
        this.voltageSupplier = voltageSupplier;
        addRequirements(whacker);
    }

    @Override
    public void initialize() {
       // hammer.hit();
    }

    @Override
    public void execute() {
        whacker.runVoltage(voltageSupplier.getAsDouble()*0.5);
    }

    @Override
    public void end(boolean interrupted) {
        whacker.runVoltage(0.0);
    }
}