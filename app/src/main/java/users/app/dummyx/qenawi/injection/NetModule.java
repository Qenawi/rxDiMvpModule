package users.app.dummyx.qenawi.injection;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import users.app.dummyx.qenawi.data.remote.ApiService;
import users.app.dummyx.qenawi.presentation.login.loginPresenter;
import users.app.dummyx.qenawi.usecase.loginUC.LoginUsecase;
import users.app.dummyx.qenawi.usecase.loginUC.LoginUsecaseImp;

import static users.app.dummyx.qenawi.injection.SchedulerType.COMPUTATION;
import static users.app.dummyx.qenawi.injection.SchedulerType.IO;
import static users.app.dummyx.qenawi.injection.SchedulerType.UI;
import static users.app.dummyx.qenawi.utils.Constants.BASE_URL;


/**
 * Created by Andorid-win on 2/4/2018.
 */
@Module
public class NetModule {
    private OkHttpClient okHttpClient;
    private ApiService apiService;//news service
    private Retrofit retrofitInistace;

    //--todo------------->>>>>>>>>>>>>>>>Provide Use cases
    @Singleton
    @Provides
    loginPresenter provideLoginPresenter(@RunOn(IO) Scheduler IOScheduler , @RunOn(UI) Scheduler mainScheduler ,
          LoginUsecase loginUsecase) {

        return new loginPresenter(IOScheduler , mainScheduler , loginUsecase);
    }

    @Singleton
    @Provides
    LoginUsecase providesLoginUsecase() {
        return new LoginUsecaseImp(providesApiService());
    }


    @Provides
    @Singleton
    ApiService providesApiService()
    {
        if (apiService == null) {
            apiService = provideRetrofit().create(ApiService.class);
        }
        return apiService;
    }//apiservice

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit()
    {
        if (retrofitInistace == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(provideOkhttpClient()).baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofitInistace = retrofit.build();
        }
        return retrofitInistace;
    }

    @Provides
    @RunOn(IO)
    Scheduler provideIo(){
        return Schedulers.io();
    }

    @Provides
    @RunOn(COMPUTATION)
    Scheduler provideComputation() {
        return Schedulers.computation();
    }

    @Provides
    @RunOn(UI)
    Scheduler provideUi() {
        return AndroidSchedulers.mainThread();
    }
}
