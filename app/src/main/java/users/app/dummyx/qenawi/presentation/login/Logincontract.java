package users.app.dummyx.qenawi.presentation.login;

import users.app.dummyx.qenawi.data.remote.model.LoginResponse;
import users.app.dummyx.qenawi.presentation.base.MvpPresenter;
import users.app.dummyx.qenawi.presentation.base.MvpView;

/**
 * Created by Andorid-win on 2/6/2018.
 */

public interface Logincontract {
    interface LoginView extends MvpView
    {
        void showError(String msg);
        void showLoading();
        void hideLoading();
        void showLoginSuccess(LoginResponse login);
        void navigateToRestScreen();
    }
    interface loginPresenter extends MvpPresenter<LoginView>
    {
        void Login(String name_phone, String pass);
        // Listner Over Here
    }
}
