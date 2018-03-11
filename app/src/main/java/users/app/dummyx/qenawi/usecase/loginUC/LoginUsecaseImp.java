package users.app.dummyx.qenawi.usecase.loginUC;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import users.app.dummyx.qenawi.App;
import users.app.dummyx.qenawi.data.remote.ApiService;
import users.app.dummyx.qenawi.data.remote.model.LoginResponse;
import users.app.dummyx.qenawi.utils.NetworkUtils;

import static users.app.dummyx.qenawi.utils.Constants.INTERNET_CONNECTION_ERROR;
import static users.app.dummyx.qenawi.utils.Constants.SERVER_ERROR;


/**
 * Created by Andorid-win on 2/4/2018.
 */

public class LoginUsecaseImp implements LoginUsecase {
    private ApiService apiService;

    public LoginUsecaseImp(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<LoginResponse> Login(String name_phone, String pass) {
        /*
        defer-> do not create the observable untill a supscriber;
        create a fresh observable on each subscription.
         */
        if (NetworkUtils.isConnected(App.getContext())) {
            return Observable.defer(() -> apiService.login(name_phone, pass).toObservable()).
                    retryWhen(throwableObservable -> throwableObservable.flatMap(throwable -> {
                if (throwable instanceof IOException) {
                    Observable.error(new Throwable(SERVER_ERROR));
                }
                return Observable.error(throwable);
            }

            ));
        } else {
            return Observable.error(new Throwable(INTERNET_CONNECTION_ERROR));
        }
    }

}
