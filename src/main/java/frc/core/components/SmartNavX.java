package frc.core.components;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.constants.NavXConstants;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.math.geometry.Rotation2d;

public class SmartNavX { 
	private final AHRS ahrs; 

	private LinearFilter pitchVelocityFilter;
	
	public SmartNavX() {
		this.ahrs = new AHRS(SPI.Port.kMXP);
		this.ahrs.resetDisplacement();
		this.pitchVelocityFilter = LinearFilter.singlePoleIIR(NavXConstants.discreteTime, NavXConstants.robotPeriodicTime);
	}
	
	public double getAngle() {
		return this.ahrs.getAngle();
	}

	public double getAltitude() {
		return this.ahrs.getDisplacementY() * 1000;
	}

	public void reset() {
		this.ahrs.reset();
	}

	public Rotation2d getRotation2d() {
		return this.ahrs.getRotation2d();
	}

	public double getRate() {
		return -(this.ahrs.getRate());
	}

	public double getPitch() {
		return this.ahrs.getPitch();
	}

	public double getCurrentPitch(double lastPitch){
		return this.getPitch() - lastPitch;
	}

	public double calculatePitchVelocity(double lastPitch){
		return this.pitchVelocityFilter.calculate(this.getCurrentPitch(lastPitch));
	}
}