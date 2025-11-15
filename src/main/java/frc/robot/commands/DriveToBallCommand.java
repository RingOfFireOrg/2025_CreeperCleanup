package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrainSubSystem;

public class DriveToBallCommand extends Command {
    private final DriveTrainSubSystem driveTrain;

    // PID-like tuning constants
    private final double kP = 0.45;      
    private final double forwardSpeed = 0.35;

    public DriveToBallCommand(DriveTrainSubSystem driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        // Read steering offset from the HTTP vision server
        double steer = Robot.visionServer.steeringOffset;

        // 999 = no detection
        if (steer == 999) {
            driveTrain.tankDrive(0, 0);
            return;
        }

        // Turn direction based on normalized steer (-1 to +1)
        double turn = steer * kP;

        double left = forwardSpeed + turn;
        double right = forwardSpeed - turn;

        driveTrain.tankDrive(left, right);
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.tankDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
