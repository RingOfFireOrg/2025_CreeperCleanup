// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import java.lang.ModuleLayer.Controller;
//import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.HammerMoveCommand;
//import frc.robot.commands.HammerSwingForwardCommand;
//import frc.robot.commands.HammerSwingBackwardCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveTrainSubSystem;
//import frc.robot.subsystems.HammerInterfaceReal;
import frc.robot.subsystems.HammerSubsystem;
//import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final XboxController driverController = new XboxController(0);
    //private final CommandXboxController ManipulatorController = new CommandXboxController(Constants.CONTROLLER_MANIPULATOR_ID);
    private final XboxController ManipulatorController = new XboxController(Constants.CONTROLLER_MANIPULATOR_ID);

    private final DriveTrainSubSystem driveTrain = new DriveTrainSubSystem();
    //private final HammerSubsystem m_hammer = new HammerSubsystem(new HammerInterfaceReal(Constants.HammerConstants.kMotorPWMPort));
    private final HammerSubsystem m_hammer = new HammerSubsystem(Constants.HammerConstants.kMotorPWMPort);

    //private final DoubleSupplier voltageSupplier; //Constants.HammerConstants.kMaxVoltage;;


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
         
        driveTrain.setDefaultCommand(
            new TankDriveCommand(driveTrain, driverController)
        );

      //  m_hammer.setDefaultCommand(
      //      new HammerMoveCommand(m_hammer, ManipulatorController)
      //  );

        configureBindings(); 
        
    }

    /*private void configureBindings() {
    }
    */
     
    private void configureBindings() {
        // Button A runs hammer at full speed while held
        new Trigger(() -> ManipulatorController.a(new EventLoop()).getAsBoolean())
            .whileTrue(new HammerMoveCommand(m_hammer, ManipulatorController, () -> 1.0));
    }

 /*     private void configureBindings() {
        // Example: Run hammer with joystick
        m_hammer.setDefaultCommand(
            new HammerMoveCommand (
                m_hammer,
                () -> -ManipulatorController.getRawAxis(1) * Constants.HammerConstants.kMaxVoltage
            )
        );
     }
*/


        //ManipulatorController.a().whileTrue(new HammerSwingForwardCommand(m_hammer));
        //ManipulatorController.b().whileTrue(new HammerSwingBackwardCommand(m_hammer));
  
/*        private void configureBindings() {
            m_hammer.setDefaultCommand(
                new RunCommand(
                    () -> m_hammer.setSpeed(-ManipulatorController.getLeftY())  // negate to make up=forward
                    )
            );

    }
  */ 
    public double GetDriverRawAxis(int axis) {
        return driverController.getRawAxis(axis);
    }

    public double GetManipulatorRawAxis(int axis) {
        return ManipulatorController.getRawAxis(axis);
    }

    // this needs a real implementation using a DIO on switch - rmackie
    public boolean getSweeperLimitSwitchValue() {
        return false;
    }

    public Command getAutonomousCommand() {
    return null;
}

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
}
