package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ServoGateConstants;

public class ServoGateSubsystem extends SubsystemBase {

    private final Servo gateServo = new Servo(ServoGateConstants.SERVO_PWM_PORT);

    public ServoGateSubsystem() {}

    public void openGate() {
        gateServo.setAngle(ServoGateConstants.OPEN_ANGLE);
    }

    public void closeGate() {
        gateServo.setAngle(ServoGateConstants.CLOSED_ANGLE);
    }
}
