package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HammerSubsystem;
//import java.util.function.DoubleSupplier;

public class HammerSwingBackwardCommand extends Command {
    private final HammerSubsystem hammer;

    public HammerSwingBackwardCommand(HammerSubsystem subsystem) {
        hammer = subsystem;
        addRequirements(hammer);
    }

    @Override
    public void initialize() {
        hammer.swingBackward();
    }

    @Override
    public void end(boolean interrupted) {
        hammer.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // Run until button released
    }
}
