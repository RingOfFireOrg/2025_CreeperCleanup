// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.SweeperCommand;

public class SweeperSubSystem extends SubsystemBase {
  private VictorSPX motorSweeper = new VictorSPX(Constants.MOTOR_SWEEPER_ID);
  /** Creates a new SweeperSubSystem. */
  public SweeperSubSystem() {
    motorSweeper.setInverted(true);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new SweeperCommand());
  }
  // The motor will rotate left or right based on the input from the user as speed variable will have Postivie and negative alues
    public void Sweep(double speed)
  {
    motorSweeper.set(VictorSPXControlMode.PercentOutput,speed);
  }

}
