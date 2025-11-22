package frc.robot.subsystems;

 import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;


// Removed the public class IntakeInterfaceReal to move it to its own file.

public class IntakeSubsystem extends SubsystemBase {
     private final SparkMax motor;

     public IntakeSubsystem() {
         motor = new SparkMax(IntakeConstants.IntakeCANID, MotorType.kBrushless);
         
         SparkMaxConfig config = new SparkMaxConfig();
         config.idleMode(IdleMode.kBrake)
               .smartCurrentLimit(20);
         
         motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
     }

     @Override
     public void periodic() {
         // Update telemetry
         SmartDashboard.putNumber("Intake/Motor Speed", motor.get());
         SmartDashboard.putNumber("Intake/Motor Current", motor.getOutputCurrent());
         SmartDashboard.putNumber("Intake/Motor Temperature", motor.getMotorTemperature());
     }

     /**
      * Set the intake motor speed directly
      * @param speed Motor speed (-1.0 to 1.0)
      */
     public void setSpeed(double speed) {
         // Clamp speed to valid range
         speed = Math.max(-1.0, Math.min(1.0, speed));
         motor.set(speed);
     }
        

     /**
      * Stop the intake motor
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
    }