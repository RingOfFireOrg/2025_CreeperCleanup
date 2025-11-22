package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    private final IntakeSubsystem intake;
    private final DoubleSupplier speedSupplier;

    public IntakeCommand(IntakeSubsystem intake, DoubleSupplier speedSupplier) {
        this.intake = intake;
        this.speedSupplier = speedSupplier;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
         // Called when command starts
     }

     @Override
     public void execute() {
         double speed = speedSupplier.getAsDouble();
         intake.setSpeed(speed);
     }

     @Override
        public void end(boolean interrupted) {
            intake.stop();
        }
}
