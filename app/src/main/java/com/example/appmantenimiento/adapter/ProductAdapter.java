package com.example.appmantenimiento.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmantenimiento.R;
import com.example.appmantenimiento.services.ProductResponse;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductResponse> productResponseList;
    private Context context;
    private ProductAdapter.ClickedItem clickedItem;

    public ProductAdapter(ProductAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }


    public void setData(  List<ProductResponse> productResponseList){
       this.productResponseList=productResponseList;
       notifyDataSetChanged();

   }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new ProductAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_stock,parent,false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        ProductResponse productResponse = productResponseList.get(position);
        String name = productResponse.getName();
        String brand = productResponse.getBrand();
        String stock = String.valueOf(productResponse.getStock());
        String unit = productResponse.getUnit();
        Log.e("lista",productResponseList.toString());
        holder._product.setText(name);
        holder._brand.setText(brand);
        holder._stock.setText(stock + " " + unit);

    }

    public interface ClickedItem{
        public void ClickedProduct(ProductResponse productResponse);
    }

    @Override
    public int getItemCount() {
        return productResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _product, _brand, _stock, _unit;
        public ViewHolder(View itemView) {
            super(itemView);
            _product=itemView.findViewById(R.id._tproduct);
            _brand=itemView.findViewById(R.id._tbrand);
            _stock=itemView.findViewById(R.id._tstock);
        }
    }
}
