package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.HammerSubsystem;
//import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoMainCommand extends SequentialCommandGroup {
    public AutoMainCommand(DriveTrainSubSystem driveTrain, HammerSubsystem hammer) {

        addCommands(
            // 1. Drive forward (tank drive) for 2 seconds
            new DriveForwardCommand(driveTrain, 0.5).withTimeout(0.5),

            // 2. Hammer forward for 5 seconds
            new HammerMoveCommand(hammer, () -> 1.0).withTimeout(5),

            // 3. Hammer reverse for 3 seconds
            new HammerMoveCommand(hammer, () -> -1.0).withTimeout(3)
        );
    }
}
