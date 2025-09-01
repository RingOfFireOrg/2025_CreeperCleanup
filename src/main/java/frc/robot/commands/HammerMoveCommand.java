package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.HammerSubsystem;
//import frc.robot.Constants;
import java.util.function.DoubleSupplier;

public class HammerMoveCommand extends Command {
    private final HammerSubsystem hammer;
    private final XboxController controller;
    private final DoubleSupplier voltageSupplier;
   // private final double duration=0.1;
    private double startTime;

    public HammerMoveCommand(HammerSubsystem hammer, XboxController controller, DoubleSupplier voltageSupplier) {
        this.hammer = hammer;
        this.voltageSupplier = voltageSupplier;
        this.controller = controller;
        addRequirements(hammer);
    }

    @Override
    public void initialize() {
       // hammer.hit();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        hammer.setVoltage(voltageSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        hammer.stop(); //runVoltage(0.0);
    }
}