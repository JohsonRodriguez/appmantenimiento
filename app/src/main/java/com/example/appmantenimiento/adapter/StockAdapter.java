package com.example.appmantenimiento.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmantenimiento.Entity.Stock;
import com.example.appmantenimiento.R;
import com.example.appmantenimiento.Entity.Product;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {
     private List<Stock> stockList;
    private Context context;
    private StockAdapter.ClickedItem clickedItem;
    public StockAdapter(StockAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }


    public void setData(List<Stock> stockList){
        this.stockList = stockList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new StockAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_stock,parent,false));

    }

     @Override
    public void onBindViewHolder(@NonNull StockAdapter.ViewHolder holder, int position) {
         Stock stock = stockList.get(position);
        String name = stock.getProduct();
        String brand = stock.getBrand();
        String amounth = String.valueOf(stock.getStock());
        String unit = stock.getUnit();
        Log.e("lista", stockList.toString());
        holder._product.setText(name);
        holder._brand.setText(brand);
        holder._stock.setText(amounth + " " + unit);

    }
    public interface ClickedItem{
        public void ClickedProduct(Product product);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _product, _brand, _stock;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _product=itemView.findViewById(R.id._tproduct);
            _brand=itemView.findViewById(R.id._tbrand);
            _stock=itemView.findViewById(R.id._tstock);
        }
    }
}
