package org.usfirst.frc.team2554.robot;

public class IOMap {
	//Mapping of the Joystick
	
	//Mapping of Buttons on the Joystick
	final public int shooterButtonNumber = 0;
	final public int rotationOffButtonNumber = 0;
	final public int autoAimButtonNumber = 0;
	/*
	 Mapping of the motors used to drive the robot
	 The order is BackRight, FrontRight, BackLeft, Front Left
	 OR
	 Back > Front && Right > Left
	 */
	final public int[] robotDriveMotorPorts = {0,1,2,3};
	//Mapping for the motors used to spin the shooter. The order does not matter.
	final public int[] shooterMotorPorts = {4,5};
	/*
	 Mapping of the motors used to move the two bars: shooter and arm
	 The first one is the arm bar. The second one is the shooter bar.
	 */
	final public int[] barMotorPorts = {6,7};
	//Mapping for the motor/actuator to push the ball to the shooters
	final public int launcherMotorPort = 8;
	//Mapping for the roller. POSSIBLE REPLACED
	final public int rollerMotorPort = 9;
	
}
