package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClamperSubsystem;

public class RunClamperCommand extends Command {
  private final ClamperSubsystem m_subsystem;
  private final double m_speed;

  /**
   * Creates a new RunClamperCommand
   * @param subsystem The mechanism subsystem to use
   * @param speed Speed to run the mechanism (-1.0 to 1.0)
   */
  public RunClamperCommand(ClamperSubsystem subsystem, double speed) {
    m_subsystem = subsystem;
    m_speed = speed;
    
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    // Called when the command is initially scheduled
  }

  @Override
  public void execute() {
    // Called every time the scheduler runs while the command is scheduled
    m_subsystem.run(m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    // Called once the command ends or is interrupted
    m_subsystem.stop();
  }

  @Override
  public boolean isFinished() {
    // This command runs until interrupted (button released)
    return false;
  }
}