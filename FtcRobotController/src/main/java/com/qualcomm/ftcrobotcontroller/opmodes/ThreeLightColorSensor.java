package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.LightSensor;

import java.util.Scanner;

/**
 * Created by SATAN on 11/27/15 NOW U DIE
 */

public class ThreeLightColorSensor
{
    public final int blueDetected = -1;
    public final int indeterminate = 0;
    public final int redDetected = 1;
    // public final int farFromBeacon = 2;

    private LightSensor red;
    private LightSensor blue;
    private LightSensor clear;


    public ThreeLightColorSensor(LightSensor redSensor, LightSensor blueSensor, LightSensor clearSensor)
    {
        red = redSensor;
        blue = blueSensor;
        clear = clearSensor;
    }


    public int colorDetected()
    {
        int redLightDetected = 0;
        int blueLightDetected = 0;
        int clearLightDetected = 0;

        for  (int i = 1; i <= 5; i++)
        {
            redLightDetected += red.getLightDetectedRaw();
            blueLightDetected += blue.getLightDetectedRaw();
            clearLightDetected += clear.getLightDetectedRaw();
        }

        int diffOfClearRed = Math.abs(clearLightDetected - redLightDetected);
        int diffOfClearBlue = Math.abs(clearLightDetected - blueLightDetected);

        //    int totalDiffofClearRed = 0;
        //  int totalDiffofClearBlue = 0;

        if (diffOfClearRed > diffOfClearBlue * 5)
        {
            return redDetected;//returns 1, meaning it's red
        }

        else if (diffOfClearBlue > diffOfClearRed * 5 ) // originally a algorithm used for finding the aveerage but if we divide, they cancel each other out
        {
            return blueDetected;//returns 1, meaning it's red
        }

        else
        {
            return indeterminate;
        }

    }

}