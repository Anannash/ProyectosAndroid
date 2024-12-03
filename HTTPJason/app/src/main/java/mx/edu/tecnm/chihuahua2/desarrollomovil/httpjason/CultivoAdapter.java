package mx.edu.tecnm.chihuahua2.desarrollomovil.httpjason;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class
CultivoAdapter extends
        RecyclerView.Adapter<CultivoAdapter.ViewHolder> {


    private Context context;
    private List<Cultivo>list;

    //Constructor


    public CultivoAdapter(Context context, List<Cultivo> list) {
        this.context = context;
        this.list = list;
    }



    /////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate
                (R.layout.single_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cultivo cultivo = list.get(position);
        //Asignar valores a los objetos de la vista
        // con los datos del cultivo en la posición correspondiente
        holder.txtIDGram.setText(cultivo.getId());
        holder.txtNombreComun.setText(cultivo.getNombreComun());
        holder.txtNombreCientifico.setText(cultivo.getNombreCientifico());
    }

    @Override
    public int getItemCount() {
        //Devolver el número de elementos en la lista
        return list.size();

    }
///////////////////////////////////////////////////////////////////////
    public class ViewHolder extends  RecyclerView.ViewHolder {
        //Objetso de la vista

        public TextView txtIDGram,
                txtNombreComun, txtNombreCientifico;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIDGram = itemView.findViewById(R.id.IDGramtxt);
            txtNombreComun = itemView.findViewById(R.id.nombrecomuntxt);
            txtNombreCientifico = itemView.findViewById(R.id.nombreCientificotxt);

        }
    }
}
