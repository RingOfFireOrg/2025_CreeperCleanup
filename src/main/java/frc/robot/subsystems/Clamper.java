package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Mechanism1Constants;

public class Clamper extends SubsystemBase {
  private final SparkMax m_motor;

  public Clamper() {
    m_motor = new SparkMax(Mechanism1Constants.kMotorCanId, MotorType.kBrushless);
    
    // Configure SparkMax using new config API
    SparkMaxConfig config = new SparkMaxConfig();
    config.idleMode(IdleMode.kBrake)
          .smartCurrentLimit(40);
    
    m_motor.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
    
    // Set current limits to protect motor
    //m_motor.setSmartCurrentLimit(40);
    
    // Burn settings to flash
    //m_motor.burnFlash();
  }

  /**
   * Run the mechanism at a specified speed
   * @param speed Speed to run (-1.0 to 1.0)
   */
  public void run(double speed) {
    // Clamp speed to valid range
    speed = Math.max(Mechanism1Constants.kMinSpeed, 
                     Math.min(Mechanism1Constants.kMaxSpeed, speed));
    m_motor.set(speed);
  }

  /**
   * Stop the mechanism
   */
  public void stop() {
    m_motor.set(0);
  }

  /**
   * Get the current motor output
   * @return Current motor output (-1.0 to 1.0)
   */
  public double getMotorOutput() {
    return m_motor.get();
  }

  /**
   * Get the motor temperature
   * @return Motor temperature in Celsius
   */
  public double getMotorTemperature() {
    return m_motor.getMotorTemperature();
  }

  /**
   * Get the motor current
   * @return Motor current in Amps
   */
  public double getMotorCurrent() {
    return m_motor.getOutputCurrent();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Add telemetry here if needed
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}