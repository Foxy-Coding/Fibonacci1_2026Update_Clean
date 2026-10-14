// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private SparkMax objLowRoller = new SparkMax(Constants.CANids.iLowRoller, MotorType.kBrushless);
  private SparkMax objHighRoller = new SparkMax(Constants.CANids.iHighRoller, MotorType.kBrushless);

  private SparkMaxConfig objLowConfig = new SparkMaxConfig();
  private SparkMaxConfig objHighConfig = new SparkMaxConfig();
  /** Creates a new Intake. */
  public Intake() {

    objLowConfig.smartCurrentLimit(60);
    objLowConfig.idleMode(IdleMode.kBrake);
    objLowConfig.openLoopRampRate(0.2);

    objHighConfig.smartCurrentLimit(60);
    objHighConfig.idleMode(IdleMode.kBrake);
    objHighConfig.openLoopRampRate(0.2);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stopIntake() {
    objLowRoller.stopMotor();
    objHighRoller.stopMotor();
  }


  // === Low Roller -> Higher speed
  // === High Roller -> Lower Speed
  public void runIntake(double dSpeedLowRoll, double dSpeedHighRoll){
    objLowRoller.set(dSpeedLowRoll);
    objHighRoller.set(dSpeedHighRoll);
  }

  public void runIntakeRPM(double dLowPRM, double dHighRPM){
    objLowRoller.set(dLowPRM);
    objHighRoller.set(dHighRPM);
  }
}
