package frc.robot.subsystems;

public interface HammerInterface {
    public static class HammerInterfaceInputs {
        public double positionDeg = 0.0;
        public double velocityDegPerSec = 0.0;
        public double appliedVolts = 0.0;
        public double currentAmps = 0.0;
    }

    public default void updateInputs(HammerInterfaceInputs inputs) {}
    public default void setVoltage(double volts) {}

    public default void swingForward() {}
    public default void swingBackward() {}
    public default void stop() {}

    public default void setSpeed(double speed) {}
}