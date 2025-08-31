// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TankDriveCommand;


public class DriveTrainSubSystem extends SubsystemBase {
  /** Creates a new DriveTrainSubSystem. */
  public DriveTrainSubSystem() {}

  //private PWMMotorController motorLeft1 = new PWMMotorController();

  private VictorSP motorLeft1 = new VictorSP(Constants.MOTOR_LEFT_1_ID);
  //private VictorSP motorLeft2 = new VictorSP(Constants.MOTOR_LEFT_2_ID);
  private VictorSP motorRight1 = new VictorSP(Constants.MOTOR_RIGHT_1_ID);
  //private VictorSP motorRight2 = new VictorSP(Constants.MOTOR_RIGHT_2_ID);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new TankDriveCommand());

  }

  public void setLeftMotors(double speed)
  {
    //motorLeft1.set(ControlMode.PercentOutput, -speed);
    motorLeft1.set(-speed);
   // motorLeft2.set(-speed);
  }

  public void setRightMotors(double speed)
  {
    motorRight1.set(speed);
    //motorRight2.set(speed);
  }
}
