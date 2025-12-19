package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.HammerSubsystem;

public class Auto extends SequentialCommandGroup {
    public Auto(DriveTrainSubSystem drivetrain, HammerSubsystem blocker) {
        addCommands(
            new AutoDriveCommand(drivetrain, 0.7, 0.55).withTimeout(1),
            new AutoDriveCommand(drivetrain, -0.5, -0.7).withTimeout(1.25),
            new AutoDriveCommand(drivetrain, -0.25, -0.3).withTimeout(0.5),
            new AutoHammerCommand(blocker, true).withTimeout(2),
            new AutoDriveCommand(drivetrain, 0,0 ).withTimeout(5),
            new AutoDriveCommand(drivetrain, 0.6, 0.5).withTimeout(1),
            new AutoDriveCommand(drivetrain, 0.5, 0.6).withTimeout(1)
        );
    }
}