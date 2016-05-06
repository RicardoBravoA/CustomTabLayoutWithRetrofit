package pe.com.orbis.tablayout.util.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.com.orbis.tablayout.util.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ricardo Bravo on 06/05/2016.
 */

public class ApiManager {

    private static ApiInterface apiInterface;

    public static ApiInterface apiInterface() {

        if(apiInterface == null){

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

            //For Log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constant.URL_BASE)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = client.create(ApiInterface.class);
        }

        return apiInterface;
    }

}
