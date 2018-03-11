package users.app.dummyx.qenawi.presentation.login;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;
import users.app.dummyx.qenawi.data.remote.model.LoginResponse;
import users.app.dummyx.qenawi.injection.RunOn;
import users.app.dummyx.qenawi.injection.SchedulerType;
import users.app.dummyx.qenawi.presentation.base.BasePresenter;
import users.app.dummyx.qenawi.usecase.loginUC.LoginUsecase;


/**
 * Created by Andorid-win on 2/4/2018.
 */

public class loginPresenter extends BasePresenter<Logincontract.LoginView> implements Logincontract.loginPresenter {
    private Scheduler mainScheduler, ioScheduler;
    private LoginResponse loginResponse;
    private LoginUsecase loginUsecase;

    @Inject
    public loginPresenter(@RunOn(SchedulerType.IO) Scheduler ioScheduler, @RunOn(SchedulerType.UI) Scheduler mainScheduler ,  LoginUsecase loginUsecase)
    {
        this.mainScheduler = mainScheduler; // return result on which thread
        this.ioScheduler = ioScheduler;  // do operation on which thread
        this.loginUsecase = loginUsecase; // to handle network Op
    }


    @Override
    public void Login(String name_phone, String pass)
    {
        checkViewAttached();
        getView().showLoading();


        addDisposable(loginUsecase.Login(name_phone, pass)
                .subscribeOn(ioScheduler).observeOn(mainScheduler).subscribeWith(new DisposableObserver<LoginResponse>()
                {
                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        loginPresenter.this.loginResponse = loginResponse;
                        getView().showLoginSuccess(loginResponse);
                        //    getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                        getView().hideLoading();
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                }));//add disposaple
    }//login
}
