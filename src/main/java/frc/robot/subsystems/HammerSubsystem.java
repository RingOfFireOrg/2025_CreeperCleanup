package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.subsystems.HammerInterface;
//import frc.robot.subsystems.HammerInterface.HammerInterfaceInputs;

public class HammerSubsystem extends SubsystemBase implements HammerInterface {
    //private final HammerInterface io;
    private final HammerInterface.HammerInterfaceInputs inputs = new HammerInterfaceInputs();

    private final PWMSparkMax motor;
    private double lastAppliedVolts = 0.0;

    public HammerSubsystem(int pwmChannel) {
        motor = new PWMSparkMax(pwmChannel);
        motor.stopMotor();
    }

    @Override
    public void periodic() {
        updateInputs(inputs);
        SmartDashboard.putNumber("Hammer/Voltage", lastAppliedVolts);
    }

    public void updateInputs(HammerInterfaceInputs inputs) {
        inputs.appliedVolts = lastAppliedVolts;
    }

    public void setVoltage(double speed) {
        motor.set(speed);
    }

    public void runVoltage(double volts) {
        lastAppliedVolts = volts;
        motor.setVoltage(volts);
    }

    public double getPosition() {
        return inputs.positionDeg;
    }

    public double getVelocity() {
        return inputs.velocityDegPerSec;
    }

    public void swingForward() {
        motor.set(0.5);
    }   

    public void swingBackward() {
        motor.set(-0.5); 
      }   

    public void stop() {
        motor.set(0);
      }  

    public void setSpeed(double speed) {
        motor.set(speed);
    }

    public void hitForTime(double seconds) {
        swingForward();
        Timer.delay(seconds);
        stop();
    }

}