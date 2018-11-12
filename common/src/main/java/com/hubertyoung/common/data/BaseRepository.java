package com.hubertyoung.common.data;


import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.baserx.LiveBus;

/**
 */
public class BaseRepository extends AbsRepository {

	protected void sendData( Object eventKey, Object t ) {
		sendData( eventKey, null, t );
	}

	protected void sendData( Object eventKey, String tag, Object t ) {
		LiveBus.getDefault().postEvent( eventKey, tag, t );
	}

	protected void showPageState( Object eventKey, String state ) {
		sendData( eventKey, state );
	}

	protected void showPageState( Object eventKey, String tag, String state ) {
		sendData( eventKey, tag, state );
	}

	public void showDialogLoading(String title) {
		LiveBus.getDefault().getUC().getShowDialogEvent().postValue(title);
	}

	public void stopLoading() {
		LiveBus.getDefault().getUC().getDismissDialogEvent().call();
	}
}
