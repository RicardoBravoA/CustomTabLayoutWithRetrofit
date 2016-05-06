package pe.com.orbis.tablayout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import pe.com.orbis.tablayout.R;
import pe.com.orbis.tablayout.adapter.PlaceAdapter;
import pe.com.orbis.tablayout.model.entity.DataEntity;
import pe.com.orbis.tablayout.model.response.PlaceResponse;
import pe.com.orbis.tablayout.util.api.ApiManager;
import pe.com.orbis.tablayout.util.control.SimpleDividerItemDecoration;
import pe.com.orbis.tablayout.util.http.ConnectionDetector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 29/04/16.
 */

public class OneFragment extends Fragment{

    private RecyclerView recyclerView;
    private PlaceAdapter placeAdapter;
    private List<PlaceResponse.DataBean> placeList;
    private String data = "All";

    public OneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        placeList = new ArrayList<>();

        placeAdapter = new PlaceAdapter(getActivity(), placeList);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getApplicationContext()
        ));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(placeAdapter);

        recyclerView.addOnItemTouchListener(new PlaceAdapter.RecyclerTouchListener(getActivity(),
                recyclerView, new PlaceAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), placeList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        getData(data);

        return view;
    }

    private void getData(String description){

        if(!ConnectionDetector.validateConnection(getActivity())) {
            Toast.makeText(getActivity(), getString(R.string.connect_internet), Toast.LENGTH_SHORT).show();
        } else {

            Call<PlaceResponse> call = ApiManager.apiInterface().getPlace(description);
            call.enqueue(new Callback<PlaceResponse>() {
                @Override
                public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> data) {

                    if(data.isSuccessful()){

                        PlaceResponse response = data.body();

                        if(response.get_meta().getStatus().equals(getString(R.string.success))){
                            placeList = response.getData();

                            Log.i("x- list", new Gson().toJson(placeList));

                            placeAdapter = new PlaceAdapter(getActivity(), placeList);
                            recyclerView.setAdapter(placeAdapter);
                            placeAdapter.notifyDataSetChanged();


                        }else{
                            Toast.makeText(getActivity(), getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<PlaceResponse> call, Throwable t) {
                    Log.i("x- error", t.getMessage());
                    Toast.makeText(getActivity(), getString(R.string.error_data), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onDataEntity(DataEntity data) {
        getData(data.getData());
    }

}