package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.HammerSubsystem;

public class HammerAutoCommand extends SequentialCommandGroup {
    public HammerAutoCommand(HammerSubsystem hammer) {

        addCommands(
            // Move hammer forward (positive speed) for 5 seconds
            new HammerMoveCommand(hammer, () -> 1.0).withTimeout(5),

            // Move hammer in reverse (negative speed) for 3 seconds
            new HammerMoveCommand(hammer, () -> -1.0).withTimeout(3)
        );
    }
}
