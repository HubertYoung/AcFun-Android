package com.hubertyoung.backgroundjob;
//       _       _    _     _
//__   _(_)_ __ | | _(_) __| |
//\ \ / / | '_ \| |/ / |/ _` |
// \ V /| | |_) |   <| | (_| |
//  \_/ |_| .__/|_|\_\_|\__,_|
//        |_|

/* guolei2@vipkid.com.cn
 *
 *2018/11/2
 */

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

import java.lang.reflect.Field;


@SuppressWarnings("WeakerAccess")
public final class ReflectHelper {
  public static Class getClass(String name) throws ClassNotFoundException {
    return Class.forName(name);
  }

  @SuppressWarnings("SameParameterValue")
  public static Field getField(Class clz, String fieldName) throws NoSuchFieldException {
    Field field = clz.getField(fieldName);
    field.setAccessible(true);
    return field;
  }


  public static Field getAiFlagsField() throws Exception {
    Class clz;
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      clz = Class.forName("android.system.StructAddrinfo");
    }else {
      clz = Class.forName("libcore.io.StructAddrinfo");
    }
    Field field = clz.getDeclaredField("ai_flags");
    field.setAccessible(true);
    return field;
  }

  public static int getAINUMERICHOST() throws Exception{
    Class clz;
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      clz = Class.forName("android.system.OsConstants");
    }else {
      clz = Class.forName("libcore.io.OsConstants");
    }
    Field field = clz.getField("AI_NUMERICHOST");
    field.setAccessible(true);
    return (int) field.get(null);
  }

}
