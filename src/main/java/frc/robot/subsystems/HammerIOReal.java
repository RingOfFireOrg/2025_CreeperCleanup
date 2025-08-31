package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Timer;
//import frc.robot.subsystems.HammerIO;

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

    public void hit() {
        motor.set(1.0);  // Full speed forward
    }

    public void retract() {
        motor.set(-0.5); // Half speed reverse
    }

    public void stop() {
        motor.set(0);
    }

    public void hitForTime(double seconds) {
        hit();
        Timer.delay(seconds);
        stop();
    }
}