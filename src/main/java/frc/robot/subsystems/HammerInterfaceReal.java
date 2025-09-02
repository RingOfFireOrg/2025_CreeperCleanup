

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
//import com.revrobotics.RelativeEncoder;
//import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.Timer;
//import frc.robot.subsystems.HammerInterface;
import frc.robot.Constants.HammerConstants;

public class HammerInterfaceReal implements HammerInterface {
    //private final PWMSparkMax motor;
    private final SparkMax motor;
    private SparkMaxConfig config = new SparkMaxConfig();
    public static final int HammerCANID = HammerConstants.HammerCANID;;
    private double lastAppliedVolts = 0.0;

    public HammerInterfaceReal(int pwmChannel) {
        //motor = new PWMSparkMax(pwmChannel);
        config.idleMode(IdleMode.kBrake).smartCurrentLimit(25);
        motor = new SparkMax(HammerCANID, MotorType.kBrushless);
        motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        //motor.setSmartCurrentLimit(10);
    }

    @Override
    public void updateInputs(HammerInterfaceInputs inputs) {
        inputs.appliedVolts = lastAppliedVolts;

        if (DriverStation.isDisabled()) {
            motor.setVoltage(0);
        }
    }

    @Override
    public void setVoltage(double volts) {
        lastAppliedVolts = volts;
        motor.setVoltage(volts * HammerConstants.VoltageFactor);
    }

    //public void hit() {
    @Override
    public void swingForward() {
        motor.set(0.2);  //Half speed forward
    }

//    public void retract() {
    public void swingBackward() {
        motor.set(-0.2); // Half speed reverse
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