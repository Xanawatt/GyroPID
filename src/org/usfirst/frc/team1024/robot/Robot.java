
package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.ResetEncoders;
import org.usfirst.frc.team1024.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Command resetEncoders;
	public static OI driverControls;
	public static Drive DriveAuto = new Drive();
	
    public void robotInit() {
    	driverControls = new OI();
    	resetEncoders = new ResetEncoders();
    	
    }
    
    public void disabledInit(){
    	DriveAuto.rightEncoder.reset();
    	DriveAuto.gyro.reset();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
        DriveAuto.Dashboard();
	}
	
    public void autonomousInit() {
    	DriveAuto.rightEncoder.reset();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	DriveAuto.rightEncoder.reset();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        driverControls.getOI();
        DriveAuto.Dashboard();
        SmartDashboard.putNumber("Gyro Raw Output", DriveAuto.gyro.getAngle());
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
