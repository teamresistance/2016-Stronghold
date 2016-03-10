package org.teamresistance.util.joystick;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.wpi.first.wpilibj.GenericHID;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/* TODO hierarchical runner */
@RunWith(MockitoJUnitRunner.class)
public class ButtonTest {

    public static final int BUTTON_ID = 0;

    @Mock private GenericHID joystick;
    @Mock private Button.Listener listener;

    @Test
    public void buttonPressed_WithListener_ShouldNotifyListener() {
        // Queue a button press and attach the listener
        when(joystick.getRawButton(BUTTON_ID)).thenReturn(false, true);
        Button button = new Button(joystick, BUTTON_ID);
        button.addPressListener(listener);

        // Update the state of the button
        button.update();

        // Verify the listener was notified of the button press
        verify(listener, times(1)).onButtonPress();
    }


    @Test
    public void buttonPressed_WithMultipleListeners_ShouldNotifyAllListeners() {
        // Queue a button press and attach the listeners
        when(joystick.getRawButton(BUTTON_ID)).thenReturn(false, true);
        Button button = new Button(joystick, BUTTON_ID);
        Button.Listener listener2 = Mockito.mock(Button.Listener.class);
        button.addPressListener(listener);
        button.addPressListener(listener2);

        // Update the state of the button
        button.update();

        // Verify the listeners were notified of the button press
        verify(listener, times(1)).onButtonPress();
        verify(listener2, times(1)).onButtonPress();
    }

    @Test
    public void buttonPressed_AfterRemovingListener_ShouldNotNotifyFormerListener() {
        // Queue a button press and attach the listener
        when(joystick.getRawButton(BUTTON_ID)).thenReturn(false, true);
        Button button = new Button(joystick, BUTTON_ID);
        button.addPressListener(listener);
        button.removePressListener(listener);

        // Update the state of the button
        button.update();

        // Verify the former listener is not notified of the button press
        verify(listener, never()).onButtonPress();
    }

    @Test
    public void buttonNotPressed_WithListener_ShouldNotNotifyListener() {
        // Queue a non-button press and attach the listener
        when(joystick.getRawButton(BUTTON_ID)).thenReturn(false, false);
        Button button = new Button(joystick, BUTTON_ID);
        button.addPressListener(listener);

        // Update the state of the button
        button.update();

        // Verify the listener is not notified of the non-button press
        verify(listener, never()).onButtonPress();
    }

}