package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.HammerSubsystem;
import frc.robot.commands.AutoDriveCommand;

public class Auto extends SequentialCommandGroup {
    public Auto(DriveTrainSubSystem drivetrain, HammerSubsystem blocker) {
        addCommands(
            new AutoDriveCommand(drivetrain, 0.8, 0.5).withTimeout(1),
            new AutoDriveCommand(drivetrain, -0.5, -0.5).withTimeout(1.25),
            new AutoDriveCommand(drivetrain, -0.25, -0.3).withTimeout(0.5),
            new HammerMoveCommand(blocker, () -> 0.5)   
        );
    }
}