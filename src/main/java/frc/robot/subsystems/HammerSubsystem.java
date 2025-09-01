package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.subsystems.HammerInterface;
import frc.robot.subsystems.HammerInterface.HammerInterfaceInputs;

public class HammerSubsystem extends SubsystemBase {
    private final HammerInterface io;
    private final HammerInterfaceInputs inputs = new HammerInterfaceInputs();

    public HammerSubsystem(HammerInterface io) {
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

    public void swingForward() {
      io.swingForward();
    }   

    public void swingBackward() {
        io.swingBackward();
      }   

    public void stop() {
        io.stop();
      }  

    public void setSpeed(double speed) {
        io.setSpeed(speed);
    }

}