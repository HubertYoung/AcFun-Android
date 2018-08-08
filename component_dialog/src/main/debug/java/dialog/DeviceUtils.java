package dialog;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/8/8 11:09
 * @since:V$VERSION
 * @desc:dialog
 */
public class DeviceUtils {
	private static ARCH a = ARCH.Unknown;

	/* compiled from: BL */
	public enum ARCH {
		Unknown,
		ARM,
		X86,
		MIPS,
		ARM64
	}

	public static synchronized ARCH a() throws Throwable {
		Throwable e;
		ARCH arch;
		synchronized (DeviceUtils.class) {
			byte[] bArr = new byte[20];
			File file = new File( Environment.getRootDirectory(), "lib/libc.so");
			if (file.canRead()) {
				RandomAccessFile randomAccessFile = null;
				try {
					RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
					try {
						randomAccessFile2.readFully(bArr);
						int i = bArr[18] | (bArr[19] << 8);
						if (i == 3) {
							a = ARCH.X86;
						} else if (i == 8) {
							a = ARCH.MIPS;
						} else if (i == 40) {
							a = ARCH.ARM;
						} else if (i != 183) {
							StringBuilder stringBuilder = new StringBuilder();
							stringBuilder.append("libc.so is unknown arch: ");
							stringBuilder.append(Integer.toHexString(i));
							Log.e("NativeBitmapFactory", stringBuilder.toString());
						} else {
							a = ARCH.ARM64;
						}
						if (randomAccessFile2 != null) {
							try {
								randomAccessFile2.close();
							} catch (IOException e2) {
								e = e2;
							}
						}
					} catch (FileNotFoundException e3) {
						e = e3;
						randomAccessFile = randomAccessFile2;
//						kpd.a(e);
						if (randomAccessFile != null) {
							try {
								randomAccessFile.close();
							} catch (IOException e4) {
								e = e4;
							}
						}
						arch = a;
						return arch;
					} catch (IOException e5) {
						e = e5;
						randomAccessFile = randomAccessFile2;
						try {
//							kpd.a(e);
							if (randomAccessFile != null) {
								try {
									randomAccessFile.close();
								} catch (IOException e6) {
									e = e6;
								}
							}
							arch = a;
							return arch;
						} catch (Throwable th) {
							e = th;
							randomAccessFile2 = randomAccessFile;
							if (randomAccessFile2 != null) {
								try {
									randomAccessFile2.close();
								} catch (Throwable e7) {
//									kpd.a(e7);
								}
							}
							throw e;
						}
					} catch (Throwable th2) {
						e = th2;
						if (randomAccessFile2 != null) {
							try {
								randomAccessFile2.close();
							} catch (Throwable e72) {
//								kpd.a(e72);
							}
						}
						throw e;
					}
				} catch (FileNotFoundException e8) {
					e = e8;
//					kpd.a(e);
					if (randomAccessFile != null) {
						try {
							randomAccessFile.close();
						} catch (IOException e42) {
							e = e42;
						}
					}
					arch = a;
					return arch;
				} catch (IOException e9) {
					e = e9;
//					kpd.a(e);
					if (randomAccessFile != null) {
						try {
							randomAccessFile.close();
						} catch (IOException e62) {
							e = e62;
						}
					}
					arch = a;
					return arch;
				}
			}
			arch = a;
		}
		return arch;
//		kpd.a(e);
//		arch = a;
//		return arch;
	}

	public static String b() {
		return Build.CPU_ABI;
	}

	public static String c() {
		try {
			Field declaredField = Build.class.getDeclaredField("CPU_ABI2");
			if (declaredField == null) {
				return null;
			}
			Object obj = declaredField.get(null);
			if (obj instanceof String) {
				return (String) obj;
			}
			return null;
		} catch (Exception unused) {
			return null;
		}
	}

	public static boolean a(String str) {
		String b = b();
		boolean z = true;
		if (!TextUtils.isEmpty(b) && b.equalsIgnoreCase(str)) {
			return true;
		}
		if (TextUtils.isEmpty(c()) || !b.equalsIgnoreCase(str)) {
			z = false;
		}
		return z;
	}

	public static boolean d() {
		return Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") && Build.PRODUCT.equalsIgnoreCase("dredd");
	}

	public static boolean e() {
		return Build.MANUFACTURER.equalsIgnoreCase("MagicBox") && Build.PRODUCT.equalsIgnoreCase("MagicBox");
	}

	public static boolean f() {
		return d() || e();
	}

	public static boolean g() throws Throwable {
		return (a("armeabi-v7a") || a("armeabi")) && ARCH.ARM.equals(a());
	}

	public static boolean h() throws Throwable {
		return a("x86") || ARCH.X86.equals(a());
	}

	public static void main(String[] arg){
		try {
			a();
		} catch ( Throwable throwable ) {
			throwable.printStackTrace();
		}
	}
}
