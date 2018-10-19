package com.hubertyoung.component_acfunarticle.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 18:22
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class ServerChannel implements Parcelable {

	public ServerChannel() {
	}

	public static final int CAN_CONTRIBUTE = 1;
	@JSONField(name = "contributeStatus" )
	public int contributeStatus;
	@JSONField(name = "id" )
	public int id;
	@JSONField(name = "img" )
	public String img;
	@JSONField(name = "name" )
	public String name;
	@JSONField(name = "pid" )
	public int pid;
	@JSONField(name = "realm" )
	public List< ServerChannel > realm;
	@JSONField(name = "children" )
	public List< ServerChannel > subChannels;

	public String toString() {
		return this.name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeInt( this.contributeStatus );
		dest.writeInt( this.id );
		dest.writeString( this.img );
		dest.writeString( this.name );
		dest.writeInt( this.pid );
		dest.writeList( this.realm );
		dest.writeList( this.subChannels );
	}

	protected ServerChannel( Parcel in ) {
		this.contributeStatus = in.readInt();
		this.id = in.readInt();
		this.img = in.readString();
		this.name = in.readString();
		this.pid = in.readInt();
		this.realm = new ArrayList< ServerChannel >();
		in.readList( this.realm, ServerChannel.class.getClassLoader() );
		this.subChannels = new ArrayList< ServerChannel >();
		in.readList( this.subChannels, ServerChannel.class.getClassLoader() );
	}

	public static final Parcelable.Creator< ServerChannel > CREATOR = new Parcelable.Creator< ServerChannel >() {
		@Override
		public ServerChannel createFromParcel( Parcel source ) {
			return new ServerChannel( source );
		}

		@Override
		public ServerChannel[] newArray( int size ) {
			return new ServerChannel[ size ];
		}
	};
}
