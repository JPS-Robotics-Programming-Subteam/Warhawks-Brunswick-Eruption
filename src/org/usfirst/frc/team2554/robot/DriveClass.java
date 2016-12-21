package org.usfirst.frc.team2554.robot;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveClass {
	public void drive(){
		//Assuming drivetrain ports are 0,1,2,3
		//Make the robot drive forward at 0.25 speed.
		RobotDrive obj = new RobotDrive(0,1,2,3);
		obj.arcadeDrive(0.25, 0);
	}
}
