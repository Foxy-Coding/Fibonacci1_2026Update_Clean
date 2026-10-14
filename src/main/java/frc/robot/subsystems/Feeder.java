// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants.CANids;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {
  private TalonFX objFeeder = new TalonFX(CANids.iFeeder, "canivore");
  private StatusCode objStatusCode;

  private final VelocityVoltage velocityRequest = new VelocityVoltage(0.0).withSlot(0);
  /** Creates a new Feeder. */
  public Feeder() {

    TalonFXConfiguration objTalonFXConfig = new TalonFXConfiguration();
    objTalonFXConfig.CurrentLimits.SupplyCurrentLimit = 30.0;
    objTalonFXConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    objTalonFXConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    objTalonFXConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = 0.2;
    objStatusCode = StatusCode.StatusCodeNotInitialized;
    for (int i = 1; i < 5; i++) {
      objStatusCode = objFeeder.getConfigurator().apply(objTalonFXConfig);
      if (objStatusCode.isOK()) break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void stopFeeder(){
    objFeeder.stopMotor();
  }

  public void runFeeder(double dSpeed){
    objFeeder.set(dSpeed);
  }

  public void runFeederRPM(double dTargetRPM){
    objFeeder.setControl(velocityRequest.withVelocity(dTargetRPM / 60.0));
  }
}
