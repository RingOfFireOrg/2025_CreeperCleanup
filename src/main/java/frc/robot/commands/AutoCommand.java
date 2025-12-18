
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.HammerSubsystem;

/** Add your docs here. */
public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(DriveTrainSubSystem driveTrain, HammerSubsystem hammer){

        addCommands(
         
            //new ShooterCommand(shooter, ()->-0.5).withTimeout(5)
            new AutoDriveCommand(driveTrain, -1, -2).withTimeout(0.45),
            new HammerMoveCommand(hammer, ()->0.9).withTimeout(1)
        );
    } 
}