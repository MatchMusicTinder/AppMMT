package com.example.mmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<String> resultsList;
    private final Context context;

    public RecyclerViewAdapter(Context context, List<String> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_principal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = resultsList.get(position);
        holder.bind(name);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView datosPublicantes;

        public ViewHolder(View itemView) {
            super(itemView);
            datosPublicantes = itemView.findViewById(R.id.datosPublicante);
        }

        public void bind(String name) {
            datosPublicantes.setText(name);
        }
    }
}
