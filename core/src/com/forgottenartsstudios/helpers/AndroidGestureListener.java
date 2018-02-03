package com.forgottenartsstudios.helpers;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Perfekt on 2/3/2018.
 */

public class AndroidGestureListener implements GestureListener {
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (Variables.inInventory) {
            Variables.tapCount = count;
            if (count > 1) {
                Variables.tapCount = 2;
            }
            System.out.println("TAP! count: " + Variables.tapCount);
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        System.out.println("LONGPRESS!");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
