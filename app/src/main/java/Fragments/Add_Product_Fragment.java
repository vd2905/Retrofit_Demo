package Fragments;

import static android.app.Activity.RESULT_OK;
import static com.example.retrofit_demo.Splash_Activity.preferences;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.retrofit_demo.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import Models.Add_Product_Class;
import Models.Instance_class;
import Models.Register_Class;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product_Fragment extends Fragment {
    ImageView imageView;
    EditText fname, fstock, fprice, fcategory;
    TextView submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_product_fragment, container, false);

        imageView = view.findViewById(R.id.addimage);
        fname=view.findViewById(R.id.namefield);
        fstock=view.findViewById(R.id.stockfield);
        fprice=view.findViewById(R.id.pricefield);
        fcategory=view.findViewById(R.id.categoryfield);
        submit = view.findViewById(R.id.submit);

        String from = preferences.getString("from",null);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String imagedata = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imagedata = Base64.getEncoder().encodeToString(byteArray);
                }

                addfragment(new View_Product_Fragment());

                if(from.equals("add"))
                {
                    Instance_class.Callapi().addproduct(preferences.getInt("uid",0),fname.getText().toString(),fstock.getText().toString(),fprice.getText().toString(),fcategory.getText().toString(),imagedata).enqueue(new Callback<Add_Product_Class>() {
                        @Override
                        public void onResponse(Call<Add_Product_Class> call, Response<Add_Product_Class> response) {
                            if(response.body().getConnection()==1)
                            {
                                if(response.body().getProductaddd()==1)
                                {
                                    Toast.makeText(getContext(), "Product Add Successfully", Toast.LENGTH_LONG).show();
                                }else
                                {
                                    Toast.makeText(getContext(), "Failed to Add Product", Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Add_Product_Class> call, Throwable t)
                        {
                            Log.d("TTT", "onFailure: off = "+t.getLocalizedMessage());
                        }

                    });
                }
                if(from.equals("update"))
                {
                    Glide.with(Add_Product_Fragment.this).load("https://darshanvarsani.000webhostapp.com/MySite/"+preferences.getString("pimage",null)).into(imageView);
                    fname.setText(preferences.getString("pname",null));
                    fstock.setText(preferences.getString("pstock",null));
                    fprice.setText(preferences.getString("pprice",null));
                    fcategory.setText(preferences.getString("pcategory",null));
                    Instance_class.Callapi().updateproduct(preferences.getString("pname",null), preferences.getString("pprice",null), preferences.getString("pstock",null), preferences.getString("pcategory",null),preferences.getString("pid",null)).enqueue(new Callback<Register_Class>() {
                        @Override
                        public void onResponse(Call<Register_Class> call, Response<Register_Class> response) {
                            if(response.body().getConnection()==1)
                            {
                                if(response.body().getConnection()==1)
                                {
                                    Log.d("RRR", "onResponse: update result = "+response.body().getResult());
                                    Toast.makeText(getContext(), "Update pref", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Register_Class> call, Throwable t)
                        {
                            Log.d("TTT", "onFailure: off = "+t.getLocalizedMessage());
                        }

                    });
                }
            }
        });
        return view;
    }
    private void addfragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }
    public void imageChooser()
    {
        CropImage.activity()
                .start(getContext(),this);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}