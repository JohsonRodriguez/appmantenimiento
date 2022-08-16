package com.example.appmantenimiento.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmantenimiento.Dto.OutputProductTotalDto;
import com.example.appmantenimiento.Dto.ProductMonth;
import com.example.appmantenimiento.R;

import java.util.List;

public class ProductMonthAdapter extends RecyclerView.Adapter<ProductMonthAdapter.ViewHolder> {
    private List<ProductMonth> productList;
    private Context context;
    private ProductMonthAdapter.ClickedItem clickedItem;
    public ProductMonthAdapter(ProductMonthAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }

    public void setData(List<ProductMonth> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductMonthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new ProductMonthAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_productbymonth,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ProductMonthAdapter.ViewHolder holder, int position) {
        ProductMonth productMonth=productList.get(position);
        String name= productMonth.getName();
        Double total=productMonth.getTotal();
        String unit= productMonth.getUnit();
        Log.e("lista", productList.toString());
        holder._product.setText(name);
        holder._stock.setText(total.toString()+" "+unit);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _product, _stock;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _product=itemView.findViewById(R.id._tproductMonth);
            _stock=itemView.findViewById(R.id._tstockMonth);

        }
    }

    public interface ClickedItem {
        public void ClickedProductMonth(ProductMonth iproductMonth);
    }
}
