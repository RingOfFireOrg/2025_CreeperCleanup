package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.subsystems.HammerIO;
import frc.robot.subsystems.HammerIO.HammerIOInputs;

public class HammerSubsystem extends SubsystemBase {
    private final HammerIO io;
    private final HammerIOInputs inputs = new HammerIOInputs();

    public HammerSubsystem(HammerIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        SmartDashboard.putNumber("Hammer/Voltage", inputs.appliedVolts);
    }

    public void runVoltage(double volts) {
        io.setVoltage(volts);
    }

    public double getPosition() {
        return inputs.positionDeg;
    }

    public double getVelocity() {
        return inputs.velocityDegPerSec;
    }
}