package com.example.appmantenimiento.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmantenimiento.Dto.AllInputDto;
import com.example.appmantenimiento.Dto.AllOutputs;
import com.example.appmantenimiento.Entity.Output;
import com.example.appmantenimiento.R;

import java.util.List;

public class AllOutputAdapter extends RecyclerView.Adapter<AllOutputAdapter.ViewHolder> {
    private List<Output> allOutputsList;
    private Context context;
    private AllOutputAdapter.ClickedItem clickedItem;
    public AllOutputAdapter(AllOutputAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }

    public void setData(List<Output> allOutputsList){
        this.allOutputsList = allOutputsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _productName, _locationName, _productAmount, _userName, _employeeName,_unit,_productBrand;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _productName=itemView.findViewById(R.id._tproduct);
            _productBrand=itemView.findViewById(R.id._tbrandout);
            _locationName=itemView.findViewById(R.id._tvLocation);
            _productAmount=itemView.findViewById(R.id._tvstock);
            _userName=itemView.findViewById(R.id._tvuser);
            _employeeName=itemView.findViewById(R.id._tvEmployee);
            _unit=itemView.findViewById(R.id._tvunit);

        }}

    @NonNull
    @Override
    public AllOutputAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new AllOutputAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_all_outputs,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AllOutputAdapter.ViewHolder holder, int position) {
        Output allOutputs=allOutputsList.get(position);
        String pName = allOutputs.getProduct();
        String pBrand = allOutputs.getBrand();
        String pLocation = allOutputs.getLocation();
        String users = allOutputs.getUsers();
        String pAmount = String.valueOf(allOutputs.getAmount());
        String eName= allOutputs.getEmployee();
        String unit= allOutputs.getUnit();
        holder._productName.setText(pName);
        holder._productBrand.setText(pBrand);
        holder._locationName.setText(pLocation);
        holder._userName.setText(users);
        holder._productAmount.setText(pAmount);
        holder._employeeName.setText(eName);
        holder._unit.setText(unit);
    }

    @Override
    public int getItemCount() {
        return allOutputsList.size();
    }
    public interface ClickedItem {
        public void ClickedOutputs(AllOutputs allOutputs);
    }

}
