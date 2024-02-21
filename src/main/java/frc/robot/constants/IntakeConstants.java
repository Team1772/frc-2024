package frc.robot.constants;

public final class IntakeConstants {
    
    public static final class Motors {
        public static final int
            id = 8;

        public static final boolean 
            isMotorInverted = true;
    }

    public static final class Sensor {
      public static final int
          infrared = 5;  
      public static final int
          climber = 2;  
    }
    public static final class Speeds {
        public static final double
            speedCollect = -0.6,
            speedRelease = 0.7,
            speedMultiplier = 0.5;
    }


    public static final class Seconds {
        public static final int
            autoShootSeconds = 3;
    }
}
