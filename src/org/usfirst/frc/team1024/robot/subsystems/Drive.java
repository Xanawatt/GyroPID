package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem implements PIDSource, PIDOutput {

    public VictorSP right = new VictorSP(RobotMap.rightDrivePWM);
    public VictorSP left = new VictorSP(RobotMap.leftDrivePWM);
    public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB, false);
    public PIDController pid;
    public AnalogGyro gyro = new AnalogGyro(RobotMap.gyroChannel);
    
    public Drive(){
    	pid = new PIDController(Constants.kp, Constants.ki, Constants.kd, Constants.kf, this, this);
    	pid.setAbsoluteTolerance(1.0);
    	pid.setOutputRange(-1.0, 1.0);
    	rightEncoder.setDistancePerPulse(0.0062831); // distance per pulse = pi/500
    	//gyro.setSensitivity(0.0068); // default is 0.007
    }
    
    public void initDefaultCommand() {
    	//Left blank intentionally
    }

	@Override
	public void pidWrite(double output) {
		right.set(output);
		left.set(-output);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		//Left blank intentionally
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return gyro.pidGet();
	}
	
	public void Dashboard() {
		SmartDashboard.putData("Gyro PID Values", pid);
		SmartDashboard.putNumber("Gyro PID Output", pidGet());
	}
}

