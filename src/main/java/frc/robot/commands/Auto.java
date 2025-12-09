package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;
import frc.robot.subsystems.HammerSubsystem;

public class Auto extends Command {
    private final DriveTrainSubSystem drivetrain;
    private final HammerSubsystem blocker;
    private long start_time;

    public Auto(DriveTrainSubSystem drivetrain, HammerSubsystem blocker) {
        this.drivetrain = drivetrain;
        this.blocker = blocker;
        addRequirements(drivetrain, blocker);
    }

    @Override
    public void initialize() {
        start_time = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        long elapsed = System.currentTimeMillis() - start_time;
        
    }
}
