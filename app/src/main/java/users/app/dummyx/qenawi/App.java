package users.app.dummyx.qenawi;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import java.lang.ref.WeakReference;

import users.app.dummyx.qenawi.injection.AppModule;
import users.app.dummyx.qenawi.injection.DaggerNetComponent;
import users.app.dummyx.qenawi.injection.NetComponent;
import users.app.dummyx.qenawi.injection.NetModule;


/**
 * Created by Andorid-win on 2/4/2018.
 */

public class App extends Application
{
    private static WeakReference<Context> context;
    private NetComponent netComponent;
    @Override
    public void onCreate()
    {
        super.onCreate();
        context = new WeakReference<>(this);
        /*if (LeakCanary.isInAnalyzerProcess(this))
        {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static Context getContext()
    {
        return context.get();
    }

    public NetComponent getNetComponent()
    {
        return netComponent;
    }
}
