// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;

public class Drive extends SubsystemBase {
  SwerveDrive drive;

  /** Creates a new Drive. */
  public Drive() {
    try {
      drive = new SwerveParser(new File(Filesystem.getDeployDirectory(), "swerve")).createSwerveDrive(Units.feetToMeters(14.5));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void driveRobotOriented(double x, double y, double z) {
    Translation2d translation = new Translation2d(x, y);

    drive.drive(translation, z, false, true, new Translation2d());
  }

  public void stop() {
    drive.drive(new Translation2d(), 0, false, true, new Translation2d());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
