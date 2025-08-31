package frc.robot.subsystems;

//import org.littletonrobotics.junction.AutoLog;

public interface HammerIO {
    //@AutoLog
    public static class HammerIOInputs {
        public double positionDeg = 0.0;
        public double velocityDegPerSec = 0.0;
        public double appliedVolts = 0.0;
        public double currentAmps = 0.0;
    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(HammerIOInputs inputs) {}

    /** Run the hammer at the specified voltage. */
    public default void setVoltage(double volts) {}
}