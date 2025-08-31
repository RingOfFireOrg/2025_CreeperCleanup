package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.subsystems.interfaces.HammerIO;

public class HammerIOReal implements HammerIO {
    private final PWMSparkMax motor;
    private double lastAppliedVolts = 0.0;

    public HammerIOReal(int pwmChannel) {
        motor = new PWMSparkMax(pwmChannel);
    }

    @Override
    public void updateInputs(HammerIOInputs inputs) {
        inputs.appliedVolts = lastAppliedVolts;
    }

    @Override
    public void setVoltage(double volts) {
        lastAppliedVolts = volts;
        motor.setVoltage(volts);
    }
}