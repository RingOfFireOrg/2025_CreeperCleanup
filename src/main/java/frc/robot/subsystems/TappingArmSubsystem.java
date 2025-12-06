package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ArmConstants;

public class TappingArmSubsystem extends SubsystemBase {
    private final SparkMax motor;

    public TappingArmSubsystem() {
        motor = new SparkMax(ArmConstants.ArmCANID, MotorType.kBrushless);
        
        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(IdleMode.kBrake)
              .smartCurrentLimit(25);
        
        motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }
    @Override
    public void periodic() {
        // Update telemetry
        
    }

    /**
      * Set the hammer motor speed directly
      * @param speed Motor speed (-1.0 to 1.0)
      */
      public void setSpeed(double speed) {
        // Clamp speed to valid range
        speed = Math.max(-0.05, Math.min(0.20, speed));
        motor.set(speed);
    }
       

    /**
     * Stop the hammer motor
     */
    public void stop() {
       motor.set(0);
    }

    /**
     * Get the current motor speed
     * @return Current motor speed (-1.0 to 1.0)
     */
    public double getSpeed() {
        return motor.get();
    }
   
    public void reset() {
       motor.set(0); 
   }
}
