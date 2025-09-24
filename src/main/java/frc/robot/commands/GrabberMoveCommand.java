package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CreeperGrabberSubsystem;

public class GrabberMoveCommand extends Command{
    private final CreeperGrabberSubsystem grabber;
    private final DoubleSupplier pivotVoltageSupplier;
    private final DoubleSupplier wheelsVoltageSupplier;
    
    public GrabberMoveCommand(CreeperGrabberSubsystem grabber, DoubleSupplier pivotVoltageSupplier, DoubleSupplier wheelsVoltageSupplier) {
        this.grabber = grabber;
        this.pivotVoltageSupplier = pivotVoltageSupplier;
        this.wheelsVoltageSupplier = wheelsVoltageSupplier;
        addRequirements(grabber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        grabber.runPivotVoltage(0.15 * pivotVoltageSupplier.getAsDouble());
        grabber.runWheelsVoltage(0.15 * wheelsVoltageSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        grabber.runPivotVoltage(0.0);
        grabber.runWheelsVoltage(0.0);
    }
}
