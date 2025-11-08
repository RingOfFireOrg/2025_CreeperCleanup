package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;

public class DriveToBallCommand extends Command {
    private final DriveTrainSubSystem driveTrain;
    private final NetworkTable visionTable;

    private final double kP = 0.45;      // steering gain
    private final double forwardSpeed = 0.35;

    public DriveToBallCommand(DriveTrainSubSystem driveTrain) {
        this.driveTrain = driveTrain;
        this.visionTable = NetworkTableInstance.getDefault().getTable("vision");
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        double steer = visionTable.getEntry("steer").getDouble(999);

        if (steer == 999) {
            driveTrain.tankDrive(0, 0);  // no target
            return;
        }

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
