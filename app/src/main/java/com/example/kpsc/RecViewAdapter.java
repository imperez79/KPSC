package com.example.kpsc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpsc.model.Operation;



import java.util.ArrayList;
import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.OperationViewHolder> {

    private   List<Operation> operations;
    private  onOperationClickListener onOperationClickListener;

    public RecViewAdapter() {
    }

    public RecViewAdapter(onOperationClickListener onOperationClickListener, List<Operation> operations) {
        this.onOperationClickListener = onOperationClickListener;
        this.operations = operations;
    }

    public RecViewAdapter(ArrayList<Operation> operations) {
        this.operations = operations;
    }


    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new OperationViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull OperationViewHolder holder, int position) {
        holder.name_oper.setText(operations.get(position).getNameOperation());
        if(operations.get(position).getPathImage()!=null){
            holder.picture.setImageURI(operations.get(position).getPathImage());
        }
       else {
               holder.picture.setImageResource(operations.get(position).getLabelTypeWork());
       }
       holder.setupTime.setText(String.valueOf( operations.get(position).getTimeSetup()));
       holder.timeTotal.setText(String.valueOf(operations.get(position).getTimeOfWork()));

        holder.opCV.setOnClickListener(view -> {

            Operation operation = operations.get(position);
            operation.setId(position);
            onOperationClickListener.onOperationClick(operation);
        });
    }
    void updateOperation(int position,Operation operation){
        operations.set(position,operation);
        notifyItemChanged(position);

    }
    void setOperations(List<Operation>operations){
        this.operations = operations;
        notifyDataSetChanged();
    }
    void addOperation(Operation operation){
        operations.add(operation);
        notifyItemInserted(operations.size()-1);
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class OperationViewHolder extends RecyclerView.ViewHolder{

        CardView opCV;
        TextView name_oper;
        TextView timeTotal;
        TextView setupTime;
        ImageView picture;


        public OperationViewHolder(@NonNull View itemView) {
            super(itemView);
            opCV = itemView.findViewById(R.id.card_view_row);
            name_oper = itemView.findViewById(R.id.text_name_operation);
            picture = itemView.findViewById(R.id.operation_image);
            timeTotal= itemView.findViewById(R.id.work_time_adapter);
            setupTime =itemView.findViewById(R.id.setup_time_adapter);

        }

        public void bind(Operation operation){

            name_oper.setText(operation.getNameOperation());
            timeTotal.setText(operation.getNameOperation());
            setupTime.setText(operation.getNameOperation());


        }

    }
    public  interface  onOperationClickListener {
        void onOperationClick(Operation operation);}
}
