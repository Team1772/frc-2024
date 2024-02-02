package frc.robot.constants;

public final class IntakeConstants {
    
    public static final class Motors {
        public static final int
            motor = 8;

        public static final boolean 
            isMotorUpperInverted = false,
            isMotorLowerInverted = true;
    }

    public static final class Sensor {
        public static final int
            cubeInfrared = 3;
    }

    public static final class Speeds {
        public static final double
            speedCollect = -0.33,
            speedRelease = 0.22,
            speedShootMid = 0.27,
            speedShootHigh = 0.37,
            

            speedShootSuperHigh = 1;
    }

    public static final class Solenoid {
        public static final int
            solenoidOpen = 6,
            solenoidClose = 7;
    }

    public static final class Seconds {
        public static final int
            autoShootSeconds = 3;
    }
}
