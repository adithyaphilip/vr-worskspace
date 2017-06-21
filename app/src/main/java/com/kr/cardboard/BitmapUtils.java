package com.kr.cardboard;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BitmapUtils {
    public static Bitmap getStitchedBitmap(int width, int height, Bitmap[] bitmaps) {
        try {
            Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
            Bitmap bmp = Bitmap.createBitmap(width, height, conf); // this creates a MUTABLE bitmapf
            Canvas canvas = new Canvas(bmp);
            Paint background = new Paint();
            background.setColor(Color.BLACK);
            canvas.drawRect(0, 0, width, height, background);

            canvas.drawBitmap(bitmaps[0], width / 2 - bitmaps[0].getWidth(),
                    height / 2 - bitmaps[0].getHeight(), null);

            canvas.drawBitmap(bitmaps[1], width / 2, height / 2 - bitmaps[1].getHeight(), null);

            canvas.drawBitmap(bitmaps[2], width / 2 - bitmaps[2].getWidth(), height / 2, null);

            canvas.drawBitmap(bitmaps[3], width / 2, height / 2, null);

            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(width, height, conf); // this creates a MUTABLE bitmapf
        return bmp;
    }
}
