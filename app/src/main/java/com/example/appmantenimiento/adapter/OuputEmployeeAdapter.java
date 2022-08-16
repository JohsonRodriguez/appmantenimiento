package com.example.appmantenimiento.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmantenimiento.Dto.EmployeeTotalDto;
import com.example.appmantenimiento.Dto.OutputProductTotalDto;
import com.example.appmantenimiento.R;

import java.util.List;

public class OuputEmployeeAdapter extends RecyclerView.Adapter<OuputEmployeeAdapter.ViewHolder>{
    private List<EmployeeTotalDto> outputEmployeeList;
    private Context context;
    private OuputEmployeeAdapter.ClickedItem clickedItem;
    public OuputEmployeeAdapter(OuputEmployeeAdapter.ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }

    public void setData(List<EmployeeTotalDto> outputEmployeeList){
        this.outputEmployeeList = outputEmployeeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OuputEmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new OuputEmployeeAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_product_employee,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull OuputEmployeeAdapter.ViewHolder holder, int position) {
      EmployeeTotalDto employeeTotalDto=outputEmployeeList.get(position);
      String name = employeeTotalDto.getEmployee();
      Double total = employeeTotalDto.getTotal();
      String unit= employeeTotalDto.getUnit();
      holder._productEmployee.setText(name);
      holder._stockEmployee.setText(total.toString()+" "+ unit);
    }

    @Override
    public int getItemCount() {
        return outputEmployeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _productEmployee, _stockEmployee;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _productEmployee=itemView.findViewById(R.id._tproductEmployee);
            _stockEmployee=itemView.findViewById(R.id._tstockEmployee);

        }
    }


    public interface ClickedItem {
        public void ClickedEmployee(EmployeeTotalDto employeeTotalDto);
    }
}
