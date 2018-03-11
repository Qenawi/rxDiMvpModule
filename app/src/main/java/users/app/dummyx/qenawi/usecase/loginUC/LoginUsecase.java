package users.app.dummyx.qenawi.usecase.loginUC;


import io.reactivex.Observable;
import io.reactivex.Single;
import users.app.dummyx.qenawi.data.remote.model.LoginResponse;

/**
 * Created by Andorid-win on 2/4/2018.
 */

public interface LoginUsecase
{
    Observable<LoginResponse> Login(String name_phone, String pass);
}
