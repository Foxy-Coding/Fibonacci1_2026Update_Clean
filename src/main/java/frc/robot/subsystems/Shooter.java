// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANids;

public class Shooter extends SubsystemBase {

  private TalonFX objShooter = new TalonFX(CANids.iShooter, "canivore");
  private StatusCode objStatusCode;
  /** Creates a new Shooter. */
  public Shooter() {

    TalonFXConfiguration objTalonFXConfig = new TalonFXConfiguration();
    objTalonFXConfig.CurrentLimits.SupplyCurrentLimit = 50.0;
    objTalonFXConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    objTalonFXConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    objTalonFXConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = 0.1;
    objStatusCode = StatusCode.StatusCodeNotInitialized;
    for (int i = 1; i < 5; i++) {
      objStatusCode = objShooter.getConfigurator().apply(objTalonFXConfig);
      if (objStatusCode.isOK()) break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void stopShooter(){
    objShooter.stopMotor();
  }

  public void runShooter(double dSpeed){
    objShooter.set(dSpeed);
  }

  
}
