package com.kento.component.basic.commonwidget.sectioned;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author:Yang
 * @date:2017/8/2 9:22
 * @since:v1.0
 * @desc:PageBean.java
 * @param:分页信息\默认一页20条
 */
public class PageBean implements Parcelable {
	private int page = 1;
	public int rows = 20;
	public int totalCount;
	public int totalPage;
	public boolean refresh = true;


	public int getLoadPage() {
		if ( refresh ) {
			return page = 1;
		}
		return ++page;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeInt( this.page );
		dest.writeInt( this.rows );
		dest.writeInt( this.totalCount );
		dest.writeInt( this.totalPage );
		dest.writeByte( this.refresh ? ( byte ) 1 : ( byte ) 0 );
	}

	public PageBean() {
	}

	protected PageBean( Parcel in ) {
		this.page = in.readInt();
		this.rows = in.readInt();
		this.totalCount = in.readInt();
		this.totalPage = in.readInt();
		this.refresh = in.readByte() != 0;
	}

	public static final Creator< PageBean > CREATOR = new Creator< PageBean >() {
		@Override
		public PageBean createFromParcel( Parcel source ) {
			return new PageBean( source );
		}

		@Override
		public PageBean[] newArray( int size ) {
			return new PageBean[ size ];
		}
	};
}