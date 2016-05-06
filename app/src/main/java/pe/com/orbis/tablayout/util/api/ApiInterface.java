package pe.com.orbis.tablayout.util.api;

import pe.com.orbis.tablayout.model.response.PlaceResponse;
import pe.com.orbis.tablayout.util.Constant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ricardo Bravo on 06/05/2016.
 */

public interface ApiInterface {

    //Interface
    @GET(Constant.URL_PLACE)
    Call<PlaceResponse> getPlace(@Path("description") String description);

}