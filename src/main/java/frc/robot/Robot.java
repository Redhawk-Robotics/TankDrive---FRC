// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * @Filename:Robot.java
 * @Purpose:This Robot.java File is for the functionality for the entirety of the TankDrive 
 * @Version:1.0
 * @Author:RedHawk Robotics FRC 87390`
 * @Date:1/26/2023
 */

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private final XboxController DRIVER = new XboxController(0); 

  //INIT MOTORS
  private final static CANSparkMax rightMotor1 = new CANSparkMax(1, MotorType.kBrushless); //
  private final static CANSparkMax rightMotor2 = new CANSparkMax(2, MotorType.kBrushless);
  private final static CANSparkMax leftMotor1 = new CANSparkMax(3, MotorType.kBrushless);
  private final static CANSparkMax leftMotor2 = new CANSparkMax(4, MotorType.kBrushless);

  //GROUP THE MOTORS
  private final static MotorControllerGroup leftSpeedGroup = new MotorControllerGroup(rightMotor1, rightMotor2);
  private final static MotorControllerGroup rightSpeedGroup = new MotorControllerGroup(leftMotor1, leftMotor2);

  //CREATING THE DIFFERENTIAL DRIVE OBJECT FOR THE TANKDRIVE
  protected final static DifferentialDrive robotDrive = new DifferentialDrive(leftSpeedGroup, rightSpeedGroup);


  //AUTONS
  //private final SimpleAuto simpleAuto = new SimpleAuto(startTime, robotDrive);
  
  //TO UPLOAD INFORMATION TO SMARTDASHBOARD 
  SendableChooser<String> m_chooser = new SendableChooser<>();

  private String m_autonomousCommand;

  @Override
  public void robotInit() {

    rightMotor1.setIdleMode(IdleMode.kBrake);
    rightMotor2.setIdleMode(IdleMode.kBrake);
    leftMotor1.setIdleMode(IdleMode.kBrake);
    leftMotor2.setIdleMode(IdleMode.kBrake);

    leftSpeedGroup.setInverted(false);
    rightSpeedGroup.setInverted(true);

    m_chooser.setDefaultOption("Simple Auto", "Simple");
    ///m_chooser.addOption("Auto ", "Test");
    SmartDashboard.putData(m_chooser);

  }

  @Override
  public void robotPeriodic() {
    
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    // startTime = Timer.getFPGATimestamp();
    m_autonomousCommand = m_chooser.getSelected();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      switch (m_autonomousCommand) {
        case "simple":
          SimpleAuto.init();
          break;
      }
    }
  }
  

  @Override
  public void autonomousPeriodic() {
    if (m_autonomousCommand != null) {
      switch (m_autonomousCommand) {
        case "simple":
          SimpleAuto.periodic();
          break;
        default:
          disabledInit();
          break;
      }
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    robotDrive.arcadeDrive(-DRIVER.getLeftY(), DRIVER.getRightX());
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
