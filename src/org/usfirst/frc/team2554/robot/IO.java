package org.usfirst.frc.team2554.robot;

public class IO {
	//Mapping of the Joystick/Controller
	final public static int joystickPort = 0;
	final public static int controllerPort = 1;
	//Mapping of Buttons on the Joystick
	/*
	 4 is Y Button
	 5 is X Button?
	 */
	final public static int rotationOffButtonNumber = 0;
	final public static int autoAimButtonNumber = 1;
	final public static int launchButtonNumber = 4;
	final public static int rotateLeft = 5;
	final public static int rotateRight = 6; 
	final public static int noTurnButtonNumber = 9; //this is the trigger
	final public static int slowTurnButtonNumber = 2;
	//Mapping of the Axes on the Joystick/Controller
	/*
	 1 is the L Y Axis
	 2 is L Trigger
	 3 is R Trigger
	 5 is the R Y Axis
	 */
	final public static int armBarAxis = 1;
	final public static int shooterInAxis = 2;
	final public static int shooterOutAxis = 3;
	final public static int armShooterAxis = 5;
	/*
	 Mapping of the motors used to drive the robot
	 The order is BackRight, FrontRight, BackLeft, Front Left
	 OR
	 Back > Front && Right > Left
	 */
	final public static int[] robotDriveMotorPorts = {0,1,2,3};
	//Mapping for the motors used to spin the shooter. The order does not matter.
	final public static int[] shooterMotorPorts = {4,5};
	/*
	 Mapping of the motors used to move the two bars: shooter and arm
	 The first one is the arm bar. The second one is the shooter bar.
	 */
	final public static int[] armMotorPorts = {6,7};
	//Mapping for the motor/actuator to push the ball to the shooters
	final public static int launcherMotorPort = 8;
	//Mapping for the roller. POSSIBLE REPLACED
	final public static int rollerMotorPort = 9;
	//Mapping for the distance sensor(Analog Input)
	final public static int distanceSensorPortNumber = 0;
	//Mapping for the limit switch
	final public static int limitSwitchPort = 9;
}
