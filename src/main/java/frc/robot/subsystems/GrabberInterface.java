
package frc.robot.subsystems;

public interface GrabberInterface {
    public static class CreeperInterfaceInputs {
        public double positionDeg = 0.0;
        public double pivotVelocityDegPerSec = 0.0;
        public double appliedPivotVolts = 0.0;
        public double appliedWheelVolts = 0.0;
        public double currentAmps = 0.0;
        public double wheelSpeed = 0.0;
    }

    public default void updateInputs(CreeperInterfaceInputs inputs) {}
    public default void setPivotVoltage(double volts) {}
    public default void setWheelsVoltage(double volts) {}

    public default void moveUp() {}
    public default void moveDown() {}
    public default void intakeWheels() {}
    public default void outtakeWheels() {}
    public default void stopWheels() {}
    public default void stopPivot() {}
    public default void stop() {}

    public default void setSpeed(double speed) {}
}