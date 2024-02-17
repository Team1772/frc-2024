package frc.robot.constants;

public final class IntakeMoveConstants {
    public static final class Motor {
        public static final int
            motorMove = 7;

        public static final boolean 
            isMotorMoveInverted = true;
    }

    public static final class Sensor {
        public static final int
            limitMax = 6,
            limitMin = 7;
    }

    public static final class Speed{
        public static final double
            speedUp = 1,
            speedDown = -0.9,
            speedMultiplier = 0.6;
    }

}
