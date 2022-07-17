package com.example.appmantenimiento.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmantenimiento.R;
import com.example.appmantenimiento.Entity.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
     private List<Product> productList;
    private Context context;
    private ProductAdapter.ClickedItem clickedItem;
    public ProductAdapter(ProductAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }


    public void setData(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new ProductAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_stock,parent,false));

    }

     @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        String name = product.getName();
        String brand = product.getBrand();
        String stock = String.valueOf(product.getStock());
        String unit = product.getUnit();
        Log.e("lista", productList.toString());
        holder._product.setText(name);
        holder._brand.setText(brand);
        holder._stock.setText(stock + " " + unit);

    }
    public interface ClickedItem{
        public void ClickedProduct(Product product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _product, _brand, _stock, _unit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _product=itemView.findViewById(R.id._tproduct);
            _brand=itemView.findViewById(R.id._tbrand);
            _stock=itemView.findViewById(R.id._tstock);
        }
    }
}
