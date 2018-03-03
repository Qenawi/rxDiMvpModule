package users.app.dummyx.qenawi.injection;

import javax.inject.Singleton;

import dagger.Component;
import users.app.dummyx.qenawi.presentation.login.LoginActivity;

/**
 * Created by Andorid-win on 2/4/2018.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent
{
    void inject(LoginActivity loginActivity);
}

