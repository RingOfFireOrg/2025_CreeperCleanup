package frc.robot.subsystems;


import frc.robot.commands.*;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 */
public class TeamColorDisplay extends SubsystemBase {

private DigitalOutput red;
private DigitalOutput blue;

// The LED module is active LOW, so we set these
// constants up to make the "double negative"
// easier to process mentally later.
static private final boolean ON = false;
static private final boolean OFF = true;
    
    /**
    *  This version of the constructor should appear only on the "main" branch
    *  If this code is loaded, the LEDs will flash Green.
    *  On the red branch we should change this to set red to "ON" and 
    *  on the blue branch we should set blue to "ON".
    */
    public TeamColorDisplay() {
        red = new DigitalOutput(frc.robot.Constants.DIO_RED_LEDS);
        addChild("Red",red);
 
        blue = new DigitalOutput(frc.robot.Constants.DIO_BLUE_LEDS);
        addChild("Blue",blue);

        // I think we can just do this here. We could call these again in periodic()
        // or as a result of a command, but as long as this object gets instantiated,
        // we just need to set these once. We want them to be set very early, before teleop. 
        // In fact, we want them to show as soon as the robot powers with the code.
        // 
        // @Seshu Do we need to create a command to make sure this subsystem gets instantiated?
        //              vvvvvvv

        this.setLedsOn();

    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        this.setLedsOn();
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
        this.setLedsOn();
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void setLedsOn()
    {
        red.set(TeamColorDisplay.OFF);  // change to "TeamColorDisplay.ON" on the RED branch
        blue.set(TeamColorDisplay.OFF); // change to "TeamColorDisplay.ON" on the BLUE branch
    }

}
