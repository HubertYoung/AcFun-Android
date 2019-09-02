package httpdns.api;


import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:33 AM
 * @since:V1.0
 * @desc:api
 */
public interface ApiHomeService {

	@FormUrlEncoded
	@POST("api/hrm/kq/attendanceButton/punchButton")
	@Headers({
			"User-Agent:Mozilla/5.0 (iPhone; CPU iPhone OS 12_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16B92 Qiyuesuo/physicalSDK/E-Mobile7/7.0.32"})
	Flowable< String > request( @HeaderMap Map< String, String > headMap, @FieldMap Map< String, String > map );
}
