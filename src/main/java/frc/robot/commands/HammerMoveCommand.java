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
import frc.robot.subsystems.HammerSubsystem;
import java.util.function.DoubleSupplier;

public class HammerMoveCommand extends Command {
    private final HammerSubsystem hammer;
    private final DoubleSupplier voltageSupplier;

    public HammerMoveCommand(HammerSubsystem hammer, DoubleSupplier voltageSupplier) {
        this.hammer = hammer;
        this.voltageSupplier = voltageSupplier;
        addRequirements(hammer);
    }

    @Override
    public void initialize() {
       // hammer.hit();
    }

    @Override
    public void execute() {
        hammer.runVoltage(0.15 * voltageSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        hammer.runVoltage(0.0);
    }
}