
package frc.robot.subsystems;

public interface CreeperInterface {
    public static class CreeperInterfaceInputs {
        public double positionDeg = 0.0;
        public double velocityDegPerSec = 0.0;
        public double appliedVolts = 0.0;
        public double currentAmps = 0.0;
        public double wheelSpeed = 0.0;
    }

    public default void updateInputs(CreeperInterfaceInputs inputs) {}
    public default void setVoltage(double volts) {}

    public default void moveUp() {}
    public default void moveDown() {}
    public default void intakeWheels() {}
    public default void outtakeWheels() {}
    public default void stop() {}

    public default void setSpeed(double speed) {}
}