package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ClamperConstants;

public class ClamperSubsystem extends SubsystemBase {
  private final SparkMax m_motor;
  private final RelativeEncoder m_encoder;

  public ClamperSubsystem() {
    m_motor = new SparkMax(ClamperConstants.kMotorCanId, MotorType.kBrushless);
    m_encoder = m_motor.getEncoder();
    
    // Configure SparkMax using new config API
    SparkMaxConfig config = new SparkMaxConfig();
    config.idleMode(IdleMode.kBrake)
          .smartCurrentLimit(40);
    
    m_motor.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
  }

  /**
   * Run the mechanism at a specified speed
   * @param speed Speed to run (-1.0 to 1.0)
   */
  public void run(double speed) {
    // Clamp speed to valid range
    speed = Math.max(ClamperConstants.kMinSpeed, 
                     Math.min(ClamperConstants.kMaxSpeed, speed));
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

  /**
   * Get the encoder position in rotations
   * @return Position in rotations
   */
  public double getPosition() {
    return m_encoder.getPosition();
  }

  /**
   * Reset the encoder position to zero
   */
  public void resetEncoder() {
    m_encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // Update telemetry
    SmartDashboard.putNumber("Clamper/Position", getPosition());
    SmartDashboard.putNumber("Clamper/Current", getMotorCurrent());
    SmartDashboard.putNumber("Clamper/Temperature", getMotorTemperature());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}