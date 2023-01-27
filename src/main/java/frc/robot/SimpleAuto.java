package frc.robot;

/**
 * @Filename:SimpleAuto.java
 * @Purpose:This SimpleAuto.java File is for a Simple Auton to move forward for 1.5x speed for 3 seconds
 * @Author:RedHawk Robotics FRC 8739
 * @Date:1/26/2023
 */

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class SimpleAuto extends Robot{
    private static double startTime;

    /** Creates a new SimpleAuto. */
    public SimpleAuto(double startTimelocal, DifferentialDrive robotDrive) {

    startTime = startTimelocal;
  }
  // Called when the command is initially scheduled.
  public static void init() {
    startTime = Timer.getFPGATimestamp();
  }
 // Called every time the scheduler runs while the command is scheduled.
 public static void periodic() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 3) {

    robotDrive.arcadeDrive(0, 0);

    } else if (time - startTime > 3 && time - startTime < 8) {
   
    robotDrive.arcadeDrive(.5, 0);
    
    } else {
     robotDrive.arcadeDrive(0, 0);
    }
  }
}
