package com.hubertyoung.common.stateview;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/28 16:31
 * @since:
 * @see StateEntity
 */
public class StateEntity {
	private String code;
	private String result;

	public static final class Builder {
		private String code;
		private String result;

		private Builder() {
		}

		public static Builder newInstance() {
			return new Builder();
		}

		public Builder setCode( String code ) {
			this.code = code;
			return this;
		}

		public Builder setResult( String result ) {
			this.result = result;
			return this;
		}

		public StateEntity build() {
			StateEntity stateEntity = new StateEntity();
			stateEntity.code = this.code;
			stateEntity.result = this.result;
			return stateEntity;
		}
	}

	public String getCode() {
		return code;
	}

	public String getResult() {
		return result;
	}
}
