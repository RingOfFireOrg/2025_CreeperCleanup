// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.HammerSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoHammerCommand extends Command {
  private final HammerSubsystem drawbridge;
  private final boolean open;
  private final double start_time = System.currentTimeMillis();
  /** Creates a new AutoHammerCommand. */
  public AutoHammerCommand(HammerSubsystem drawbridge, boolean open) {
    this.drawbridge = drawbridge;
    this.open = open;
    addRequirements(drawbridge);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drawbridge.setSpeed((open) ? Constants.HammerConstants.DRAWBRIDGE_SPEED : -Constants.HammerConstants.DRAWBRIDGE_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (start_time - System.currentTimeMillis()) > Constants.HammerConstants.DRAWBRIDGE_OPEN_TIME;
  }
}
