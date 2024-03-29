package com.example.appmantenimiento.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmantenimiento.Dto.AllInputDto;
import com.example.appmantenimiento.Entity.Input;
import com.example.appmantenimiento.R;

import java.util.List;

public class AllInputAdapter extends RecyclerView.Adapter<AllInputAdapter.ViewHolder> {
    private List<Input> allInputsList;
    private Context context;
    private AllInputAdapter.ClickedItem clickedItem;
    public AllInputAdapter(AllInputAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }

    public void setData(List<Input> allInputsList){
        this.allInputsList = allInputsList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _productName, _productBrand, _productAmount, _userName,_unit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _productName=itemView.findViewById(R.id._tproduct);
            _productBrand=itemView.findViewById(R.id._tbrand);
            _productAmount=itemView.findViewById(R.id._tvstock);
            _userName=itemView.findViewById(R.id._tvuser);
            _unit=itemView.findViewById(R.id._tvunit);

        }
    }

    @NonNull
    @Override
    public AllInputAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new AllInputAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_all_inputs,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AllInputAdapter.ViewHolder holder, int position) {
        Input allInputDto=allInputsList.get(position);
        String pName = allInputDto.getProduct();
        String pBrand = allInputDto.getBrand();
        String users = allInputDto.getUsers();
        String pAmount = String.valueOf(allInputDto.getAmount());
        String unit= allInputDto.getUnit();
        holder._productName.setText(pName);
        holder._productBrand.setText(pBrand);
        holder._userName.setText(users);
        holder._productAmount.setText(pAmount);
        holder._unit.setText(unit);

    }

    @Override
    public int getItemCount() {
        return allInputsList.size();
    }
    public interface ClickedItem {
        public void ClickedInputs(AllInputAdapter allInputAdapter);
    }
}
