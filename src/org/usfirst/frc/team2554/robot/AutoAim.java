package org.usfirst.frc.team2554.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutoAim {
	//Sets up a NetworkTable to receive data from RoboRealm.
	static NetworkTable server = NetworkTable.getTable("SmartDashboard");
	//X and Y values of the goal blob detected
	static double xValue, yValue;
	//Limit of acceptable zone
	static final double DEADZONE = 30;
	
	public static void run(RobotDrive myRobot, Victor shooterArm, RobotDrive shooter, Spark launcher, DigitalInput limitSwitch)
	{
		changeValues();
		//Checks if the xValue is within in the acceptable zone
		if(xValue > DEADZONE)
			//Turns the robot left
				myRobot.arcadeDrive(0,-0.1);
		if(xValue < -DEADZONE)
			//Turns the robot right
				myRobot.arcadeDrive(0,0.1);
		//Checks if the yValue is within the acceptable zone
		//Checks if the limitSwitch is down which prevents the arm from going too far
		//The acceptable zone is currently one DEADZONE above the center
		//Changes once distance sensor is added
		if(yValue < DEADZONE && !limitSwitch.get())
			//Moves arm up
				shooterArm.set(0.1);
		if(yValue > DEADZONE * 2 && !limitSwitch.get())
			//Moves arm down
				shooterArm.set(-0.1);
		//Checks if the values are in the acceptable zone
		if(xValue < DEADZONE && xValue > -DEADZONE && yValue > DEADZONE && yValue < 2*DEADZONE)
		{
			//Spins up the shooter
			shooter.arcadeDrive(0,0.6);
			//Pushes the ball into the shooter
			launcher.set(0.4);
		}
	}
	//Updates the blob values
	private static void changeValues()
	{
		//getNumber allows you to pull a double from the NetworkTable
		xValue = server.getNumber("magnitudeX", 0.0);
		yValue = server.getNumber("magnitudeY", 0.0);
			
	}
}
