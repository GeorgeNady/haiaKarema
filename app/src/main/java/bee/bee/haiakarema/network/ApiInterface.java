package bee.bee.haiakarema.network;

import java.util.List;

import bee.bee.haiakarema.model.GetProjects;
import bee.bee.haiakarema.model.ItemProject;
import bee.bee.haiakarema.model.Login;
import bee.bee.haiakarema.model.LoginResponse;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    ////////////////////////////////////////////////////////////////////////////////////////// /////
    ////////////////////////////////////////////////////////////////////////////////////////// LOGIN
    ////////////////////////////////////////////////////////////////////////////////////////// /////
    @Headers({"Accept: application/json", "Content-Type:  application/json"})
    @POST("login")
    Observable<LoginResponse> login(@Body Login login);

    @Headers({"Accept: application/json", "Content-Type:  application/json"})
    @GET("getprojects")
    Observable<List<ItemProject>> getprojects(@Header("Authorization") String authorization);



    ///////////////////////////////////////////////////////////////////////////////////////// //////
    /////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////// //////

}
