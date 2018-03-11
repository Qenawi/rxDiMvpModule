package users.app.dummyx.qenawi.data.remote;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import users.app.dummyx.qenawi.data.remote.model.LoginResponse;

import static users.app.dummyx.qenawi.utils.Constants.LOGIN_API;


/**
 * Created by Andorid-win on 2/4/2018.
 */

public interface ApiService
{
    @FormUrlEncoded
    @POST(LOGIN_API)
    Single<LoginResponse> login
            (@Field("phone") String email,
             @Field("password") String password);
}
