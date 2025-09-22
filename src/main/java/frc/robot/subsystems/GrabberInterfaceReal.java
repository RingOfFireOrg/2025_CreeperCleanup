package frc.robot.subsystems;


import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants.GrabberConstants;


public class GrabberInterfaceReal implements GrabberInterface{

    private final SparkMax pivot; 
    private final SparkMax left;
    private final SparkMax right;

    private SparkMaxConfig config = new SparkMaxConfig();
    
    public static final int PivotCANID = GrabberConstants.PivotCANID;
    public static final int LeftCANID = GrabberConstants.LeftCANID;
    public static final int RightCANID = GrabberConstants.RightCANID;

    private double lastAppliedPivotVolts = 0.0;
    private double lastAppliedWheelVolts = 0.0;

    public GrabberInterfaceReal (int pwmChannel) {
        config.idleMode(IdleMode.kBrake).smartCurrentLimit(25);
        
        pivot = new SparkMax(PivotCANID, MotorType.kBrushless);
        left = new SparkMax(LeftCANID, MotorType.kBrushless);
        right = new SparkMax(RightCANID, MotorType.kBrushless);

        pivot.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        left.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        right.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    @Override
    public void updateInputs(CreeperInterfaceInputs inputs) {
        inputs.appliedPivotVolts = lastAppliedPivotVolts;
        inputs.appliedWheelVolts = lastAppliedWheelVolts;

        if (DriverStation.isDisabled()) {
            pivot.setVoltage(0);
            left.setVoltage(0);
            right.setVoltage(0);
        }
    }

    @Override
    public void setPivotVoltage(double volts) {
        lastAppliedPivotVolts = volts;
        pivot.setVoltage(volts*GrabberConstants.VoltageFactorPivot);
    }

    @Override
    public void setWheelsVoltage(double volts){ 
        lastAppliedWheelVolts = volts;
        left.setVoltage(volts*GrabberConstants.VoltageFactorWheels);
        right.setVoltage(volts*GrabberConstants.VoltageFactorWheels);
    }

    public void moveUp() {
        pivot.set(0.2);//invert if needed based off of testing
    }
    
    public void moveDown() {
        pivot.set(-0.2);//invert if needed based off of testing
    }
    
    public void intakeWheels() {
        left.set(0.2);//invert if needed based off of testing
        right.set(-0.2);//invert if needed based off of testing
    }
    
    public void outtakeWheels() {
        left.set(-0.2);//invert if needed based off of testing
        right.set(0.2);//invert if needed based off of testing
    }

    public void stopWheels() {
        left.set(0);
        right.set(0);
    }
    
    public void stopPivot() {
        pivot.set(0);
    }
    
    public void stop() {
        pivot.set(0);
        left.set(0);
        right.set(0);
    }

}
