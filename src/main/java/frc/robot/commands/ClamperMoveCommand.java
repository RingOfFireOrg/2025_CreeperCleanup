package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClamperSubsystem;

public class ClamperMoveCommand extends Command {
  private final ClamperSubsystem m_subsystem;
  private final double m_rotations;
  private final double m_speed;
  private double m_startPosition;
  private double m_targetPosition;

  /**
   * Creates a new RotateClamperCommand that rotates the clamper a specific number of rotations
   * @param subsystem The clamper subsystem to use
   * @param rotations Number of rotations (positive or negative)
   * @param speed Speed to rotate at (0.0 to 1.0, direction determined by rotations sign)
   */
  public ClamperMoveCommand(ClamperSubsystem subsystem, double rotations, double speed) {
    m_subsystem = subsystem;
    m_rotations = rotations;
    m_speed = Math.abs(speed); // Ensure speed is positive
    
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_startPosition = m_subsystem.getPosition();
    m_targetPosition = m_startPosition + m_rotations;
    
    // Run motor in the correct direction based on rotation sign
    double motorSpeed = m_rotations > 0 ? m_speed : -m_speed;
    m_subsystem.run(motorSpeed);
  }

  @Override
  public void execute() {
    // Motor continues running at the speed set in initialize
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.stop();
  }

  @Override
  public boolean isFinished() {
    double currentPosition = m_subsystem.getPosition();
    
    // Check if we've reached the target position
    if (m_rotations > 0) {
      return currentPosition >= m_targetPosition;
    } else {
      return currentPosition <= m_targetPosition;
    }
  }
}