package br.com.philsteffen.listacomasynctesk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.philsteffen.listacomasynctesk.R;
import br.com.philsteffen.listacomasynctesk.listener.OnItemClick;
import br.com.philsteffen.listacomasynctesk.model.AndroID;

/**
 * Created by logonrm on 29/01/2018.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.AndroidItemViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<AndroID> androIDList;
    private OnItemClick listener;

    public AndroidAdapter(Context context, List<AndroID> androIDList) {
        this.androIDList = androIDList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public AndroidItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_android, parent, false);
        return new AndroidItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AndroidItemViewHolder holder, int position) {
        AndroID androID = androIDList.get(position);
        holder.tvApi.setText(androID.getApi());
        holder.tvNome.setText(androID.getNome());
        holder.tvVersao.setText(androID.getVersao());
        Picasso.with(context).load(androID.getUrlImagem())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivLogo);

    }

    public void setClickListener(OnItemClick listener){
        this.listener = listener;
    }

    public AndroID getItem(int position){
        return androIDList.get(position);
    }

    @Override
    public int getItemCount() {
        return androIDList.size();
    }

    protected class AndroidItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivLogo;
        private TextView tvNome;
        private TextView tvApi;
        private TextView tvVersao;

        public AndroidItemViewHolder(View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvApi = itemView.findViewById(R.id.tvApi);
            tvVersao = itemView.findViewById(R.id.tvVersao);
        }

        @Override
        public void onClick(View v) {
            if(listener != null) listener.onClick(v,getAdapterPosition());
        }
    }
}
