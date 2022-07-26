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

import com.example.appmantenimiento.Dto.OutputProductTotalDto;
import com.example.appmantenimiento.Entity.Product;
import com.example.appmantenimiento.R;

import java.util.List;

public class OutputProductAdapter extends RecyclerView.Adapter<OutputProductAdapter.ViewHolder> {
    private List<OutputProductTotalDto> outputProductList;
    private Context context;
    private OutputProductAdapter.ClickedItem clickedItem;
    public OutputProductAdapter(OutputProductAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }

    public void setData(List<OutputProductTotalDto> outputProductList){
        this.outputProductList = outputProductList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OutputProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new OutputProductAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_product_location,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull OutputProductAdapter.ViewHolder holder, int position) {
        OutputProductTotalDto outputProductTotalDto=outputProductList.get(position);
        String name= outputProductTotalDto.getName();
        Double total=outputProductTotalDto.getTotal();
        Log.e("lista", outputProductList.toString());
        holder._productLocation.setText(name);
        holder._stockLocation.setText(total.toString());

    }

    @Override
    public int getItemCount() {
        return outputProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _productLocation, _stockLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _productLocation=itemView.findViewById(R.id._tproductLocation);
            _stockLocation=itemView.findViewById(R.id._tstockLocation);

        }
    }

    public interface ClickedItem {
        public void ClickedProductTotalDto(OutputProductTotalDto outputProductTotalDto);
    }
}
