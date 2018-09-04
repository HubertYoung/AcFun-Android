//package dialog;
//
//import android.os.Build;
//import android.os.Environment;
//import android.text.TextUtils;
//import android.util.Log;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//
///**
// * <br>
// * function:
// * <p>
// *
// * @author:HubertYoung
// * @date:2018/8/8 11:09
// * @since:V$VERSION
// * @desc:dialog
// */
//public class DeviceUtils {
//	private static ARCH sARCH = ARCH.Unknown;
//
//	/* compiled from: BL */
//	public enum ARCH {
//		Unknown,
//		ARM,
//		X86,
//		MIPS,
//		ARM64
//	}
//
//	public static synchronized ARCH cpuType() throws Throwable {
//		Throwable e;
//		ARCH arch;
//		synchronized (DeviceUtils.class) {
//			byte[] bArr = new byte[20];
//			File file = new File( Environment.getRootDirectory(), "lib/libc.so");
//			if (file.canRead()) {
//				RandomAccessFile randomAccessFile = null;
//				try {
//					RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
//					try {
//						randomAccessFile2.readFully(bArr);
//						int i = bArr[18] | (bArr[19] << 8);
//						if (i == 3) {
//							sARCH = ARCH.X86;
//						} else if (i == 8) {
//							sARCH = ARCH.MIPS;
//						} else if (i == 40) {
//							sARCH = ARCH.ARM;
//						} else if (i != 183) {
//							StringBuilder stringBuilder = new StringBuilder();
//							stringBuilder.append("libc.so is unknown arch: ");
//							stringBuilder.append(Integer.toHexString(i));
//							Log.e("NativeBitmapFactory", stringBuilder.toString());
//						} else {
//							sARCH = ARCH.ARM64;
//						}
//						if (randomAccessFile2 != null) {
//							try {
//								randomAccessFile2.close();
//							} catch (IOException e2) {
//								e = e2;
//							}
//						}
//					} catch (FileNotFoundException e3) {
//						e = e3;
//						randomAccessFile = randomAccessFile2;
////						kpd.cpuType(e);
//						if (randomAccessFile != null) {
//							try {
//								randomAccessFile.close();
//							} catch (IOException e4) {
//								e = e4;
//							}
//						}
//						arch = sARCH;
//						return arch;
//					} catch (IOException e5) {
//						e = e5;
//						randomAccessFile = randomAccessFile2;
//						try {
////							kpd.cpuType(e);
//							if (randomAccessFile != null) {
//								try {
//									randomAccessFile.close();
//								} catch (IOException e6) {
//									e = e6;
//								}
//							}
//							arch = sARCH;
//							return arch;
//						} catch (Throwable th) {
//							e = th;
//							randomAccessFile2 = randomAccessFile;
//							if (randomAccessFile2 != null) {
//								try {
//									randomAccessFile2.close();
//								} catch (Throwable e7) {
////									kpd.cpuType(e7);
//								}
//							}
//							throw e;
//						}
//					} catch (Throwable th2) {
//						e = th2;
//						if (randomAccessFile2 != null) {
//							try {
//								randomAccessFile2.close();
//							} catch (Throwable e72) {
////								kpd.cpuType(e72);
//							}
//						}
//						throw e;
//					}
//				} catch (FileNotFoundException e8) {
//					e = e8;
////					kpd.cpuType(e);
//					if (randomAccessFile != null) {
//						try {
//							randomAccessFile.close();
//						} catch (IOException e42) {
//							e = e42;
//						}
//					}
//					arch = sARCH;
//					return arch;
//				} catch (IOException e9) {
//					e = e9;
////					kpd.cpuType(e);
//					if (randomAccessFile != null) {
//						try {
//							randomAccessFile.close();
//						} catch (IOException e62) {
//							e = e62;
//						}
//					}
//					arch = sARCH;
//					return arch;
//				}
//			}
//			arch = sARCH;
//		}
//		return arch;
////		kpd.cpuType(e);
////		arch = cpuType;
////		return arch;
//	}
//
//	public static String isCpuAbi() {
//		return Build.CPU_ABI;
//	}
//
//	public static String getCpuAbi2() {
//		if ( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ) {
//			String[] supportedAbis = Build.SUPPORTED_ABIS;
//		}else{
//			String supportedAbis = Build.CPU_ABI2;
//		}
//		return "";
//	}
//
//	public static boolean cpuType( String str) {
//		String miPushProcess = isCpuAbi();
//		boolean flag = true;
//		if (!TextUtils.isEmpty(miPushProcess) && miPushProcess.equalsIgnoreCase(str)) {
//			return true;
//		}
//		if (TextUtils.isEmpty( getCpuAbi2()) || !miPushProcess.equalsIgnoreCase(str)) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	public static boolean d() {
//		return Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") && Build.PRODUCT.equalsIgnoreCase("dredd");
//	}
//
//	public static boolean e() {
//		return Build.MANUFACTURER.equalsIgnoreCase("MagicBox") && Build.PRODUCT.equalsIgnoreCase("MagicBox");
//	}
//
//	public static boolean f() {
//		return d() || e();
//	}
//
//	public static boolean g() throws Throwable {
//		return ( cpuType("armeabi-v7a") || cpuType("armeabi")) && ARCH.ARM.equals( cpuType());
//	}
//
//	public static boolean h() throws Throwable {
//		return cpuType("x86") || ARCH.X86.equals( cpuType());
//	}
//
//	public static void main(String[] arg){
//		try {
//			cpuType();
//		} catch ( Throwable throwable ) {
//			throwable.printStackTrace();
//		}
//	}
//}
