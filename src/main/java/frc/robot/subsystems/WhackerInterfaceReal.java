package frc.robot.subsystems;


import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants.GrabberConstants;


public class WhackerInterfaceReal implements WhackerInterface{

    private final SparkMax pivot; 
    private final SparkMax right;

    private SparkMaxConfig config = new SparkMaxConfig();
    
    public static final int PivotCANID = GrabberConstants.PivotCANID;
    public static final int LeftCANID = GrabberConstants.LeftCANID;
    public static final int RightCANID = GrabberConstants.RightCANID;

    private double lastAppliedPivotVolts = 0.0;

    public WhackerInterfaceReal (int pwmChannel) {
        config.idleMode(IdleMode.kBrake).smartCurrentLimit(25);
        
        pivot = new SparkMax(PivotCANID, MotorType.kBrushless);
        right = new SparkMax(RightCANID, MotorType.kBrushless);

        pivot.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        right.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    @Override
    public void updateInputs(CreeperInterfaceInputs inputs) {
        inputs.appliedPivotVolts = lastAppliedPivotVolts;

        if (DriverStation.isDisabled()) {
            pivot.setVoltage(0);
            right.setVoltage(0);
        }
    }

    @Override
    public void setPivotVoltage(double volts) {
        lastAppliedPivotVolts = volts;
        pivot.setVoltage(volts*GrabberConstants.VoltageFactorPivot);
    }

    public void moveUp() {
        pivot.set(0.2);//invert if needed based off of testing
    }
    
    public void moveDown() {
        pivot.set(-0.2);//invert if needed based off of testing
    }
    
    public void intakeWheels() {
        right.set(-0.2);//invert if needed based off of testing
    }
    
    public void outtakeWheels() {
        right.set(0.2);//invert if needed based off of testing
    }

    public void stopWheels() {
        right.set(0);
    }
    
    public void stopPivot() {
        pivot.set(0);
    }
    
    public void stop() {
        pivot.set(0);
        right.set(0);
    }

}
