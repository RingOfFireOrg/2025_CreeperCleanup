// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.swing.SwingWorker;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class SweeperCommand extends Command {
  // this variable is meant to memorialize the last direction the sweeper was moving
  // it can be either of LEFT or RIGHT
  int formerDirection;
  public static final int LEFT = 1;
  public static final int RIGHT = 2;

  // this variable is meant to describe the motions the sweeper may take in the current 
  // move
  // If the sweeper was last moving LEFT and triggered the limit switch, then the 
  // sweeper motion should be constrained to "RIGHT_ONLY" until it clears the switch,
  // then it can go back to being "FREE".
  private int motionConstraint;
  public static final int FREE = 11;
  public static final int LEFT_ONLY = 12;
  public static final int RIGHT_ONLY = 13;

  /** Creates a new SweeperCommand. */
  public SweeperCommand() {
    addRequirements(Robot.mySweeper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Get the values from the user and from robot sensors
    // how the drive controller is pressed
    double leftStickX = Robot.m_robotContainer.GetManipulatorRawAxis(Constants.LEFT_STICK_X);
    SmartDashboard.putNumber("LeftStickValue of manipulator", leftStickX);

    // Deal with limit switch and state.
    boolean limitSwitchState = Robot.m_robotContainer.getSweeperLimitSwitchValue();
    SmartDashboard.putBoolean("Sweeper limit switch state", limitSwitchState);
    motionConstraint = computeMotionConstraint(limitSwitchState,motionConstraint, formerDirection);
    double sweepSpeed = leftStickX/2 * leftStickX/2;

    // Multiplying two negatives makes it positive so negating sweepspeed if original value is negative
    if (leftStickX <=0 )
    {
      sweepSpeed *= -1;
    }
    SmartDashboard.putNumber("sweep speed of manipulator", sweepSpeed);


    Robot.mySweeper.Sweep(sweepSpeed);

    // This is the code that actually drives the robot... 
    // We are squaring the speeds so that it grudually increases the speed
    // We are going to use the limit switch read to know if we've hit the
    // end of motion and constrain motion so it can't go further in that 
    // direction.
    /*if(leftStickX <= 0)
    {
      Robot.mySweeper.Sweep((-(leftStickX/2*leftStickX/2)));
      // assumption --- !!!! ---- Negative number is a LEFT motion! fix wires if not !!!
      if (motionConstraint == SweeperCommand.LEFT_ONLY || motionConstraint == SweeperCommand.FREE) {
        Robot.mySweeper.Sweep(((leftStickX*leftStickX)));
        // We don't want to change the recorded direction until the switch is free.
        // We set the constraints based on state every time, rather than noticing it's a transition
        // so we need to keep the direction in which we hit the limit switch until the limit switch
        // if "open" or "off".
        if (!limitSwitchState) { 
          formerDirection = SweeperCommand.LEFT; 
        }
      }
    } else {
      Robot.mySweeper.Sweep((-leftStickX/2*leftStickX/2)));
      if (motionConstraint == SweeperCommand.RIGHT_ONLY || motionConstraint == SweeperCommand.FREE) {
        Robot.mySweeper.Sweep((leftStickX*leftStickX));
        if (!limitSwitchState) { // don't want to change the recorded direction until the switch is free.
          formerDirection = SweeperCommand.RIGHT;
        }
      }
    }*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Set the motor to 0 speed.. to avoid any movement.
    Robot.mySweeper.Sweep(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  //Not using this method in sprint1
  int computeMotionConstraint(boolean state, int constraint, int lastDirection)
  {
    int newConstraint = SweeperCommand.FREE;
    if(state = true) { // limit switch hit or holding 
      switch (constraint) {
        case SweeperCommand.FREE: // time to impose a new constraint
          if (lastDirection == SweeperCommand.LEFT) {
            newConstraint = SweeperCommand.RIGHT_ONLY;
          } else { 
            newConstraint = SweeperCommand.LEFT_ONLY;
          }
          break;
        case SweeperCommand.LEFT_ONLY: // assume the current constraint is good
          break;
        case SweeperCommand.RIGHT_ONLY: // assume the current constraint is good
          break;
        }
    } else {
      switch (constraint) {
        case SweeperCommand.FREE: // no limit switch and no constraint, no action needed.
          break;
        case SweeperCommand.LEFT_ONLY: // assume the current constraint is good
          if (lastDirection == SweeperCommand.LEFT) { // the switch released! we can go free.
            newConstraint = SweeperCommand.FREE;
          } // otherwise we need to continue with left only until we free up the switch
          break;
        case SweeperCommand.RIGHT_ONLY: // assume the current constraint is good
          if (lastDirection == SweeperCommand.RIGHT) { // the switch released! we can go free.
            newConstraint = SweeperCommand.FREE;
          } // otherwise we need to continue with right only until we free up the switch
          break;
        }      
    }
    return newConstraint;
  }

}



