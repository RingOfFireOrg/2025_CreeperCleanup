package frc.robot.commands;

 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
 import frc.robot.subsystems.TappingArmSubsystem;
 import java.util.function.DoubleSupplier;

public class TappingArmCommand extends Command {
    private final TappingArmSubsystem tapper;
    private final DoubleSupplier speedSupplier;

    // Constructor
    public TappingArmCommand(TappingArmSubsystem tapper, DoubleSupplier speedSupplier) {
        this.tapper = tapper;
        this.speedSupplier = speedSupplier;
        addRequirements(tapper); // Declare subsystem dependencies
    }

    @Override
    public void execute() {
        double speed = speedSupplier.getAsDouble();
        tapper.setSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        tapper.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // Command runs indefinitely unless interrupted
    }
}