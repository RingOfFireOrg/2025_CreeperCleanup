package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;

public class AutoDriveCommand extends Command {
    private final DriveTrainSubSystem driveTrain;
    private final double speed;
    private final double duration;
    private double startTime;

    public AutoDriveCommand(DriveTrainSubSystem driveTrain, double speed, double duration) {
        this.driveTrain = driveTrain;
        this.speed = speed;
        this.duration = duration;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        startTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        driveTrain.tankDrive(speed, speed); // Drive straight forward
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.tankDrive(0, 0); // Stop motors
    }

    @Override
    public boolean isFinished() {
        return edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - startTime >= duration;
    }
}