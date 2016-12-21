
package org.usfirst.frc.team2554.robot;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Spark;

public class Robot extends SampleRobot {
    RobotDrive myRobot, shooter;
    /*
      PORT 0 MUST BE JOYSTICK
      PORT 1 MUST BE CONTROLLER
      CONTROLLER MUST BE SET TO X-INPUT
     */
    //Creation of two Joysticks. One right an actual joystick. The other is a controller. However both lies under the Joystick class.     
    Joystick joystick, controller;
    //Creation of 3 JoystickButtons which is used to receive data from buttons on a Joystick.
    JoystickButton shooterButton, rotationOffButton, autoAimButton;
    //Victors is used to move the arm bar and shooter bar up.
    Victor armBar, armShooter;
    //Sparks are used to control the launcher and roller.
    Spark launcher, roller;
    //A DEADZONE is increase zone where the arm bar and shooter bar motors are off
    final double DEADZONE = 0.15;
    //A distance sensor connected through Analog Input
    AnalogInput distanceSensor;
    double distance = 0;
    //A limit switch to stop the arm from going too far
    DigitalInput limitSwitch;
    //SendableChooser to put a list of choices onto SmartBoard
    SendableChooser chooser;
    //Magnitude
    double magnitude = 0.0;
    double turningMagnitude = 0.0;
    CameraServer server;
    //Speeds
    final double slowTurnSpeed = 0.8; //speed which the robot turns at when a bumper is held
    final double slowTurnMultiplier = 0.5; //multiplier that affects speed of slow turn with slow turn button;

    public Robot() {
    	server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");
        myRobot = new RobotDrive(IO.robotDriveMotorPorts[3], IO.robotDriveMotorPorts[2], IO.robotDriveMotorPorts[1], IO.robotDriveMotorPorts[0]);
        myRobot.setExpiration(0.1);
        joystick = new Joystick(IO.joystickPort);
        controller = new Joystick(IO.controllerPort);
        shooter = new RobotDrive(IO.shooterMotorPorts[0], IO.shooterMotorPorts[1]);
        armBar = new Victor(IO.armMotorPorts[0]);
        armShooter = new Victor(IO.armMotorPorts[1]);
        launcher = new Spark(IO.launcherMotorPort);
        roller = new Spark(IO.rollerMotorPort);
        distanceSensor = new AnalogInput(IO.distanceSensorPortNumber);
        limitSwitch = new DigitalInput(IO.limitSwitchPort);
   }
    
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Do Nothing(Default)", 'n');
        chooser.addObject("Under Low Bar", 'l');
        chooser.addObject("Rough Terrain", 'r');
        chooser.addObject("Portcullis", 'p');
        chooser.addObject("High Wall", 'w');
        SmartDashboard.putData("Auto modes", chooser);
    }

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the if-else structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomous() {

    	String autoSelected = (String) chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
    	
/*    	switch(autoSelected) {
    		case "Under Low Bar":
=======
    	char autoSelected = (char) chooser.getSelected();
    	switch(autoSelected) {
    		case 'l':
>>>>>>> Stashed changes
    			AutoMethod.lowBar();
    			break;
    		case 'r':
    			AutoMethod.roughTerrain();
    		case 'p':
    			AutoMethod.portcullis();
    		case 'w':
    			AutoMethod.highWall();
    		default:
    			//Needs more code
    			myRobot.arcadeDrive(0,0);
    			roller.set(0);
    			shooter.arcadeDrive(0,0);
    			break;	
    			
    	}
 */
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
/*        myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	//Driving the robot
        	magnitude = -joystick.getRawAxis(3) + 1;
            if( joystick.getRawButton( IO.slowTurnButtonNumber ) ) {
                turningMagnitude = -joystick.getRawAxis(3) * slowTurnMultiplier + 1; //you might wanna change the 0.5 to something else
            }
            turningMagnitude = -joystick.getRawAxis(3) + 1;


            //If the button is not pressed down then the robot will move normally
            if( !joystick.getRawButton(IO.noTurnButtonNumber) )
        	    myRobot.arcadeDrive( magnitude * -joystick.getY(), turningMagnitude * -joystick.getZ() );
            //If the button is pressed, the button will no longer turn.
            else
                myRobot.arcadeDrive( magnitude * -joystick.getY(), 0 );

            if( controller.getRawButton(IO.rotateLeft) && !controller.getRawButton(IO.rotateRight) )
                myRobot.arcadeDrive( magnitude * -joystick.getY(), slowTurnSpeed );
            if( controller.getRawButton(IO.rotateRight) && !controller.getRawButton(IO.rotateLeft) )
                myRobot.arcadeDrive( magnitude * -joystick.getY(), -slowTurnSpeed );

        	//If the axis is really close to the center(aka the DEADZONE) the arm will provide an upwards torque to combat gravity.
            if(controller.getRawAxis(IO.armBarAxis) <= DEADZONE && controller.getRawAxis(IO.armBarAxis)>= -DEADZONE)
            	armBar.set(-0.05);
            //If the axis is not in the DEADZONE, it will function normally but with a multiplier to the magnitude.
            else
            	armBar.set(controller.getRawAxis(IO.armBarAxis)/3.0);
            //Same logic as the other arm except the shooter arm is tight enough that it does not need an upwards torque to hold it up.
            if(controller.getRawAxis(IO.armShooterAxis) <= DEADZONE && controller.getRawAxis(IO.armShooterAxis) >= -DEADZONE)
            	armShooter.set(0);
            else if(!limitSwitch.get() && controller.getRawAxis(IO.armShooterAxis) > 0)
            	armShooter.set(0);
            else
            	armShooter.set(-controller.getRawAxis(IO.armShooterAxis));
            //The shooter system is set up as an arcade drive.
            //The shooter will only shoot if the trigger is past a certain point so it doesn't accidentally trigger.
            //Will only work if the other trigger is not down.
            if(controller.getRawAxis(IO.shooterOutAxis) > 0.1 && controller.getRawAxis(IO.shooterInAxis) < 0.1)
            	shooter.arcadeDrive(0, controller.getRawAxis(IO.shooterOutAxis));
            //The same concept as the out-going shooter but with less inwards spin speed.
            //When intaking, the robot's rollers will also spin.
            else if(controller.getRawAxis(IO.shooterInAxis) > 0.1 && controller.getRawAxis(IO.shooterOutAxis) < 0.1){
            	shooter.arcadeDrive(0,(-controller.getRawAxis(IO.shooterInAxis)/3.0*2));
            	roller.set(0.4);
            }
            //If both triggers are down or no triggers are down then nothing will happen.
            else{
            	roller.set(0.0);
            	shooter.arcadeDrive(0,0);
            }
            //When the Y button has been pressed, the actuator on top of the robot will push the ball to the shooter
            //The launcher has an auto-stop mechanism.
            if(controller.getRawButton(IO.launchButtonNumber))
            	//There is a multiplier to make sure the actuator doesn't move too fast.
            	launcher.set(1/2.00);
            else
            	//When ever the launcher is not used. It will always try to retract.
            	launcher.set(-1/2.00);
            //Gets the distance from the sensor. getVoltage() is very volatile. Might want to change to getAverageVoltage().
            distance = distanceSensor.getVoltage();
            //If A is pressed then then the AutoAim sequence will run
//           if(controller.getRawButton(IO.autoAimButtonNumber))
//            	AutoAim.run(myRobot, armShooter,shooter,launcher, limitSwitch);
//
            //If Left button is pressed, then the robot will slightly turn left (used for aiming)
            if(controller.getRawButton(5))
            	myRobot.arcadeDrive(0, 0.1);
            //If Right button is pressed, then the robot will slightly turn right (used for aiming)
            if(controller.getRawButton(6))
            	myRobot.arcadeDrive(0, -0.1);

        	Timer.delay(0.005);		// wait for a motor update time
        }
 */
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
