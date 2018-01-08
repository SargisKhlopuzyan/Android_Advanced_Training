package com.example.unit_test_temperature_converter;

import android.content.Context;



import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.FileOutputStream;

import static com.example.unit_test_temperature_converter.WriteConfigurationUtil.writeConfiguration;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sargiskh on 1/9/2018.
 */

public class WriteConfigurationUtilTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    Context context;

    @Mock
    FileOutputStream fileOutputStream;

    @Test
    public void writeShouldWriteTwiceToFileSystem() {
        try {
            when(context.openFileOutput(anyString(), anyInt())).thenReturn(fileOutputStream);
//            Util.writeConfiguration(context);
            writeConfiguration(context);
            verify(context, times(1)).openFileOutput(anyString(), anyInt());
            verify(fileOutputStream, atLeast(2)).write(any(byte[].class));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
