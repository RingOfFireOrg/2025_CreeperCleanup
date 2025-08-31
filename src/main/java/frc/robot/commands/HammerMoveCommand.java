package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HammerSubsystem;
import java.util.function.DoubleSupplier;

public class HammerMoveCommand extends Command {
    private final HammerSubsystem hammer;
    private final DoubleSupplier voltageSupplier;
    private final double duration=0.1;
    private double startTime=0;

    public HammerMoveCommand(HammerSubsystem hammer, DoubleSupplier voltageSupplier) {
        this.hammer = hammer;
        this.voltageSupplier = voltageSupplier;
        addRequirements(hammer);
    }

    @Override
    public void initialize() {
       // hammer.hit();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        hammer.runVoltage(voltageSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        hammer.runVoltage(0.0);
    }
}