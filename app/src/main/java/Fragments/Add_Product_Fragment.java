package Fragments;

import static android.app.Activity.RESULT_OK;

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
import androidx.navigation.ActivityNavigatorDestinationBuilderKt;

import com.example.retrofit_demo.LoginActivity;
import com.example.retrofit_demo.R;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import Models.Add_Product_Class;
import Models.Instance_class;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product_Fragment extends Fragment {
    ImageView imageView;
    EditText fname, fstock, fprice, fcategory;
    TextView submit;
    private ActivityNavigatorDestinationBuilderKt CropImage;

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

                addfragment(new Home_Fragment());

                if(LoginActivity.preferences.getString("from",null).equals("add"))
                {
                    Instance_class.Callapi().addproduct(LoginActivity.preferences.getInt("sellerid",0),fname.getText().toString(),fstock.getText().toString(),fprice.getText().toString(),fcategory.getText().toString(),imagedata).enqueue(new Callback<Add_Product_Class>() {
                        @Override
                        public void onResponse(Call<Add_Product_Class> call, Response<Add_Product_Class> response) {
                            if(response.body().getConnection()==1)
                            {
                                if(response.body().getProductadd()==1)
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
    void imageChooser()
    {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
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