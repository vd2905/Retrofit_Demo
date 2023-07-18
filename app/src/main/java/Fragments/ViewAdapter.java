package Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_demo.R;

import java.util.List;

import Models.ProductData;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder>
{
    View_Product_Fragment view_product_fragment;
    List<ProductData> productdata;

    public ViewAdapter(View_Product_Fragment view_product_fragment, List<ProductData> productdata) {
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
        holder.item.setText(""+productdata.get(position).getName());
        holder.price.setText(""+productdata.get(position).getPrice());
        holder.stock.setText(""+productdata.get(position).getStock());
        holder.category.setText(""+productdata.get(position).getCategory());

        Glide.with(view_product_fragment).load("https://darshanvarsani.000webhostapp.com/MySite/"+productdata.get(position).getImage()).into(holder.imageView);
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
        TextView item,stock,price,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.in_re_img);
            item=itemView.findViewById(R.id.in_re_title);
            stock=itemView.findViewById(R.id.in_re_stock);
            price=itemView.findViewById(R.id.in_re_price);
            category=itemView.findViewById(R.id.in_re_category);
            menuoption=itemView.findViewById(R.id.menuoption);
        }
    }
}
