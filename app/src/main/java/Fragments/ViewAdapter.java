package Fragments;

import static com.example.retrofit_demo.Splash_Activity.editor;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.retrofit_demo.PaymentActivity;
import com.example.retrofit_demo.R;

import java.util.List;

import Models.Instance_class;
import Models.Productdatum;
import Models.Register_Class;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder>
{
    View_Product_Fragment view_product_fragment;
    List<Productdatum> productdata;

    public ViewAdapter(View_Product_Fragment view_product_fragment, List<Productdatum> productdata) {
        this.view_product_fragment = view_product_fragment;
        this.productdata = productdata;
    }

    @NonNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(" "+productdata.get(position).getName());
        holder.price.setText("Price : "+productdata.get(position).getPrice());
        holder.stock.setText("Stock : "+productdata.get(position).getStock());
        holder.category.setText("Category : "+productdata.get(position).getCategory());

        Glide.with(view_product_fragment)
                .load("https://darshanvarsani.000webhostapp.com/MySite/"+productdata.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imageView);
        Log.d("TTT", "onBindViewHolder: " + productdata.get(position).getImage());

        holder.menuoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(view_product_fragment.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId()==R.id.update){

                            editor.putString("from","update");
                            editor.commit();
                            editor.putString("pid",""+productdata.get(position).getId());
                            editor.putString("pname",""+productdata.get(position).getName());
                            editor.putString("pstock",""+productdata.get(position).getStock());
                            editor.putString("pprice",""+productdata.get(position).getPrice());
                            editor.putString("pcategory",""+productdata.get(position).getCategory());
                            editor.putString("pimage",""+productdata.get(position).getImage());
                            editor.commit();

                            addfragment(new Add_Product_Fragment());
                        }
                        if(item.getItemId()==R.id.delete){

                            Instance_class.Callapi().deleteproduct(Integer.parseInt(productdata.get(position).getId())).enqueue(new Callback<Register_Class>() {
                                @Override
                                public void onResponse(Call<Register_Class> call, Response<Register_Class> response) {
                                    if(response.body().getConnection()==1){
                                        if(response.body().getResult()==1){
                                            Toast.makeText(view_product_fragment.getContext(), "Product Delete", Toast.LENGTH_LONG).show();
                                            productdata.remove(position);
                                            notifyDataSetChanged();
                                            notifyDataSetChanged();

                                        }else
                                        {
                                            Toast.makeText(view_product_fragment.getContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Register_Class> call, Throwable t) {

                                }
                            });

                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }
    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = view_product_fragment.getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,menuoption;
        TextView name,stock,price,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.in_re_img);
            name=itemView.findViewById(R.id.in_re_title);
            stock=itemView.findViewById(R.id.in_re_stock);
            price=itemView.findViewById(R.id.in_re_price);
            category=itemView.findViewById(R.id.in_re_category);
            menuoption=itemView.findViewById(R.id.menuoption);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(view_product_fragment.getContext(), PaymentActivity.class);
                    view_product_fragment.startActivity(intent);

                    return true;
                }
            });
        }
    }
}
