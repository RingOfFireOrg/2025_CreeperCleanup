package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;

public class DriveForwardCommand extends Command {
    private final DriveTrainSubSystem driveTrain;
    private final double speed;

    public DriveForwardCommand(DriveTrainSubSystem driveTrain, double speed) {
        this.driveTrain = driveTrain;
        this.speed = speed;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.setLeftMotors(speed);
        driveTrain.setRightMotors(speed);
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.setLeftMotors(0);
        driveTrain.setRightMotors(0);
    }

    @Override
    public boolean isFinished() {
        return false; // controlled by timeout externally
    }
}
