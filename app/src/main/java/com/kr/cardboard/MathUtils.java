package com.kr.cardboard;

import android.util.Log;

import java.util.Arrays;

public class MathUtils {

    public static float[] getOrientationDiff(float[] prevOrientation, float[] currOrientation) {
        float[] diffs = new float[3];
        diffs[0] = currOrientation[0] - prevOrientation[0];
        diffs[1] = currOrientation[1] - prevOrientation[1];
        diffs[2] = currOrientation[2] - prevOrientation[2];

        if (diffs[0] < 0.1) {
            diffs[0] = 0;
        }

        return diffs;
    }

    public static int getOffset(float currOrientation, float minDeg, float maxDeg, int maxVal) {
        if (currOrientation < minDeg) {
            currOrientation = minDeg;
        } else if (currOrientation > maxDeg) {
            currOrientation = maxDeg;
        }

        float normOrientation = (currOrientation - minDeg) / (maxDeg - minDeg);
        return (int) (normOrientation * maxVal);
    }

    public static void logOrs(float[] ors) {
        Float[] nOrs = new Float[ors.length];
        for(int i = 0; i < ors.length; i++) {
            nOrs[i] = ors[i];
        }
        Log.d("ors", "" + Arrays.asList(nOrs));
    }
}
