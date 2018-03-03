package users.app.dummyx.qenawi.injection;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import users.app.dummyx.qenawi.data.remote.ApiService;

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
}
