// BarcoAdapter.java
package com.example.rio;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BarcoAdapter extends RecyclerView.Adapter<BarcoAdapter.BarcoViewHolder> {

    private List<Embarcacao> barcos;
    private OnItemClickListener onItemClickListener;

    public BarcoAdapter(List<Embarcacao> barcos) {
        this.barcos = barcos;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public BarcoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new BarcoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarcoViewHolder holder, int position) {
        Embarcacao barco = barcos.get(position);

        holder.nomeTextView.setText("Nome: " + barco.getNome());
        holder.tipoTextView.setText("Tipo: " + barco.getTipo());
        holder.capacidadeTextView.setText("Capacidade: " + barco.getCapacidade());
        holder.corTextView.setText("Cor: " + barco.getCor());
        holder.precoTextView.setText("Preço: " + barco.getPreco());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(barco);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return barcos.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Embarcacao barco);
    }

    public static class BarcoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView;
        TextView tipoTextView;
        TextView capacidadeTextView;
        TextView corTextView;
        TextView precoTextView;

        public BarcoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            tipoTextView = itemView.findViewById(R.id.tipoTextView);
            capacidadeTextView = itemView.findViewById(R.id.capacidadeTextView);
            corTextView = itemView.findViewById(R.id.corTextView);
            precoTextView = itemView.findViewById(R.id.precoTextView);
        }
    }
}
