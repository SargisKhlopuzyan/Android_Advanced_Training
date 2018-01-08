package com.example.unit_test_temperature_converter;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * Created by sargiskh on 1/9/2018.
 */

public class WriteConfigurationUtil {

    public static void writeConfiguration(Context ctx ) {

        try (FileOutputStream openFileOutput = ctx.openFileOutput("config.txt", Context.MODE_PRIVATE);) {
            openFileOutput.write("This is a test1.".getBytes());
            openFileOutput.write("This is a test2.".getBytes());
        } catch (Exception e) {
            // not handled
        }
    }
}
