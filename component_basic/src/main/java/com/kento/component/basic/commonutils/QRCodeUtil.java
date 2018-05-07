package com.kento.component.basic.commonutils;//package com.kento.common.commonutils;
//
//import android.graphics.Bitmap;
//import android.util.Log;
//
//import java.util.Hashtable;
//
//import com.kento.common.qrscan.qrdecode.BarcodeFormat;
//import io.reactivex.Observable;
//import io.reactivex.schedulers.Schedulers;
//
///**
// * @author sunjianfei
// * @date 2016/7/13
// * @description
// */
//public class QRCodeUtil {
//    private static final int BLACK = 0xff000000;
//
//    private static final int PADDING_SIZE_MIN = 5; // 最小留白长度, 单位: px
//
//    private static Bitmap createQRCode( String str, int widthAndHeight) throws WriterException {
//        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//        BitMatrix matrix = new MultiFormatWriter().encode(str,
//                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, hints);
//
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        int[] pixels = new int[width * height];
//
//        boolean isFirstBlackPoint = false;
//        int startX = 0;
//        int startY = 0;
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (matrix.get(x, y)) {
//                    if (isFirstBlackPoint == false) {
//                        isFirstBlackPoint = true;
//                        startX = x;
//                        startY = y;
//                        Log.d("createQRCode", "x y = " + x + " " + y);
//                    }
//                    pixels[y * width + x] = BLACK;
//                }
//            }
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//
//        // 剪切中间的二维码区域，减少padding区域
//        if (startX <= PADDING_SIZE_MIN) return bitmap;
//
//        int x1 = startX - PADDING_SIZE_MIN;
//        int y1 = startY - PADDING_SIZE_MIN;
//        if (x1 < 0 || y1 < 0) return bitmap;
//
//        int w1 = width - x1 * 2;
//        int h1 = height - y1 * 2;
//
//        Bitmap bitmapQR = Bitmap.createBitmap(bitmap, x1, y1, w1, h1);
//
//        return bitmapQR;
//    }
//
//    /**
//     * @param url   生成二维码的链接
//     * @param width 二维码的宽高
//     * @return
//     */
//    public static Observable<Bitmap> createQrImage( final String url, final int width) {
//        return Observable.<Bitmap>create( subscriber -> {
//			try {
//				Bitmap bitmap = createQRCode(url, width);
//				subscriber.onNext(bitmap);
//				subscriber.onComplete();
//			} catch (WriterException e) {
//				e.printStackTrace();
//				subscriber.onError(e);
//			}
//		} ).subscribeOn( Schedulers.io());
//    }
//}
