package com.juziwl.commonlibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/05/23
 * @description
 */
public class BlurUtils {

    public static Bitmap blur(Context context, Bitmap src, @IntRange(from = 1, to = 25) int radius) {
        if (src == null) {
            return null;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                android.renderscript.RenderScript renderScript = android.renderscript.RenderScript.create(context);
                android.renderscript.Allocation input = android.renderscript.Allocation.createFromBitmap(renderScript, src);
                android.renderscript.Allocation output = android.renderscript.Allocation.createTyped(renderScript, input.getType());
                android.renderscript.ScriptIntrinsicBlur scriptIntrinsicBlur = android.renderscript.ScriptIntrinsicBlur.create(renderScript,
                        android.renderscript.Element.U8_4(renderScript));
                scriptIntrinsicBlur.setRadius(radius);
                scriptIntrinsicBlur.setInput(input);
                scriptIntrinsicBlur.forEach(output);
                output.copyTo(src);
                return src;
            } else {
                RenderScript renderScript = RenderScript.create(context);
                Allocation input = Allocation.createFromBitmap(renderScript, src);
                Allocation output = Allocation.createTyped(renderScript, input.getType());
                ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
                scriptIntrinsicBlur.setRadius(radius);
                scriptIntrinsicBlur.setInput(input);
                scriptIntrinsicBlur.forEach(output);
                output.copyTo(src);
                return src;
            }
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
