// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.ShooterSubsystem;

/** Add your docs here. */
public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(DriveTrainSubSystem driveTrain, ShooterSubsystem shooter){

        addCommands(

            //new ShooterCommand(shooter, ()->-0.5).withTimeout(5)
            new AutoDriveCommand(driveTrain, 1, 1).withTimeout(0.5),
            new AutoDriveCommand(driveTrain, -1, 0).withTimeout(0.8),
            new AutoDriveCommand(driveTrain, -1, -1).withTimeout(0.5)
        );
    } 
}