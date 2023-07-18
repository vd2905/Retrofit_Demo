package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_demo.R;
import com.example.retrofit_demo.Splash_Activity;

import Models.Instance_class;
import Models.View_Product_Class;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_Product_Fragment extends Fragment {
    ViewAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_fragment, container, false);

        Instance_class.Callapi().viewproduct(Splash_Activity.preferences.getInt("id", 0)).enqueue(new Callback<View_Product_Class>() {
            @Override
            public void onResponse(Call<View_Product_Class> call, Response<View_Product_Class> response) {
                adapter = new ViewAdapter(View_Product_Fragment.this, response.body().getProductdata());
                recyclerView = view.findViewById(R.id.recycleview);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<View_Product_Class> call, Throwable t) {

            }
        });

        return view;
    }
}