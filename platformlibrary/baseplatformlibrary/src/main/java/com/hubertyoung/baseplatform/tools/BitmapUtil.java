/*
 * Copyright (C) 2015 Bilibili <jungly.ik@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hubertyoung.baseplatform.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 10:18
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.tools
 */
public class BitmapUtil {

    public static byte[] bmpToByteArray( final Bitmap bmp, int maxSize, final boolean needRecycle) {
        if (bmp == null) {
            return null;
        }

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        int options = 100;
        bmp.compress( Bitmap.CompressFormat.JPEG, options, output);
        int outputLength = output.size();
        while (outputLength > maxSize) {
            if (outputLength > 10 * maxSize) {
                options -= 30;
            } else if (outputLength > 5 * maxSize) {
                options -= 20;
            } else {
                options -= 10;
            }

            output.reset();
            bmp.compress( Bitmap.CompressFormat.JPEG, options, output);
            outputLength = output.size();
        }

        if (needRecycle) {
            bmp.recycle();
        }

        final byte[] result = output.toByteArray();
        IOUtil.closeQuietly(output);

        return result;
    }

    public static Bitmap decodeUrl( String imageUrl) {
        if ( TextUtils.isEmpty(imageUrl)) {
            return null;
        }

        InputStream is = null;
        try {
            is = new URL(imageUrl).openStream();
            if (is.available() < 50 * 1024) {
                return BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(is);
        }

        return null;
    }

    public static Bitmap decodeFile( String path, float width, float height) {
        int inSampleSize = getInSampleSize(path, width, height);
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inSampleSize = inSampleSize;
        newOpts.inJustDecodeBounds = false;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path, newOpts);
    }

    private static int getInSampleSize( String path, float width, float height) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, newOpts);
        int outWidth = newOpts.outWidth;
        int outHeight = newOpts.outHeight;
        return (int) getScale(width, height, outWidth, outHeight);
    }

    public static double getScale(float targetWidth, float targetHeight, float bmpWidth, float bmpHeight) {
        double be;
        if (bmpWidth >= bmpHeight) {
            float widthScale = bmpWidth / targetHeight;
            float heightScale = bmpHeight / targetWidth;
            if (widthScale >= heightScale) {
                be = Math.rint(widthScale);
            } else {
                be = Math.rint(heightScale);
            }
        } else {
            float widthScale = bmpWidth / targetWidth;
            float heightScale = bmpHeight / targetHeight;
            if (widthScale >= heightScale) {
                be = widthScale;
            } else {
                be = heightScale;
            }
        }
        if (be <= 0) {
            return 1.0;
        }

        return be;
    }

    public static File saveBitmapToExternal( Bitmap bitmap, String targetFileDirPath) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        File targetFileDir = new File(targetFileDirPath);
        if (!targetFileDir.exists() && !targetFileDir.mkdirs()) {
            return null;
        }

        File targetFile = new File(targetFileDir, UUID.randomUUID().toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, baos);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);
            baos.writeTo(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                baos.flush();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return targetFile;
    }
}
