package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Timer;
//import frc.robot.subsystems.HammerInterface;

public class HammerInterfaceReal implements HammerInterface {
    private final PWMSparkMax motor;
    private double lastAppliedVolts = 0.0;

    public HammerInterfaceReal(int pwmChannel) {
        motor = new PWMSparkMax(pwmChannel);
    }

    @Override
    public void updateInputs(HammerInterfaceInputs inputs) {
        inputs.appliedVolts = lastAppliedVolts;
    }

    @Override
    public void setVoltage(double volts) {
        lastAppliedVolts = volts;
        motor.setVoltage(volts);
    }

    //public void hit() {
    @Override
    public void swingForward() {
        motor.set(0.5);  //Half speed forward
    }

//    public void retract() {
    public void swingBackward() {
        motor.set(-0.5); // Half speed reverse
    }

    public void setSpeed(double speed) {
        motor.set(speed);
    }

    public void stop() {
        motor.set(0);
        //motor.stopMotor();
    }

    public void hitForTime(double seconds) {
        swingForward();
        Timer.delay(seconds);
        stop();
    }
}