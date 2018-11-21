package com.hubertyoung.common.net.config;

/**
 * @author:Yang
 * @date:2017/7/25 10:12
 * @since:v1.0
 * @desc:com.hubertyoung.common.net.config
 * @param:对应网络状态码
 */
public enum NetStatus {
	Server_Fail("服务器抽风中~", -1),
	Success("成功", 0),
	Server_Success("成功", 0),
//	User_Not_Login("用户未登录", "1002"),
////	User_Is_Lock("用户已锁定", "1002"),
//	Device_Not_Exist("设备不存在", "1003"),
//	Device_Exist("设备已经存在", "1004"),
//	Failed("失败", "1005"),
//	Account_Login_Other_Device("该账号已在你其他设备上登录", "1006"),
//	Mobile_Is_Empty("手机号为空", "1007"),
//	Mobile_Format_Is_wrone("手机号格式有误", "1008"),
//	Code_Is_Empty("验证码为空", "1009"),
//	Code_Is_Expired("验证码已过期", "1010"),
//	User_Not_Exist("用户不存在", "1011"),
//	UploadNot("资料上传不全，请确认", "1012"),
//	SaveorUpdate_Fail("失败，请重试", "1012"),
//	Illegal_Parameter("非法参数", "1014"),
//	PayOrder_Evaluated("该订单已评价", "1015"),
//	Evaluation_failed("评价失败。请重试", "1015"),
	Error("异常", -2);
//	File_Is_Large("文件太大", "1017"),
//	Code_Is_Wrong("验证码错误", "1018"),
//	Unbound_Cell_Phone_Number("未绑定手机号", "1030");

	// 成员变量
	private String name;
	private int index;

	// 构造方法
	NetStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (NetStatus c : NetStatus.values()) {
			if ( c.getIndex() == index) {
				return c.name;
			}
		}
		return "";
	}

	// 普通方法
	public static int getString(String str) {
		for (NetStatus c : NetStatus.values()) {
			if (c.getName().equals(str)) {
				return c.index;
			}
		}
		return -1;
	}


	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
