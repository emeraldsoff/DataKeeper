package inc.frontlooksoftwares.datakeeper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class cli_adapter extends FirestoreRecyclerAdapter<clicard_gen, cli_adapter.cli_holder> {
    public cli_adapter(@NonNull FirestoreRecyclerOptions<clicard_gen> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull cli_holder holder, int position, @NonNull clicard_gen model) {
//        try{
//
//        }catch (Exception e){
//            Toast.makeText(clicard_adapter.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
        holder.cli_name.setText(model.getClient_name());
        holder.cli_id.setText(model.getId());
        holder.cli_phno.setText(model.getMobile_no());
//        holder.cli_dob.setText(model.getDob());
    }

    @NonNull
    @Override
    public cli_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cid_card, parent, false);
        return new cli_holder(view);
    }

    class cli_holder extends RecyclerView.ViewHolder {
        TextView cli_name;
        TextView cli_id;
        TextView cli_dob;
        TextView cli_phno;

        public cli_holder(View itemView) {
            super(itemView);
            cli_name = itemView.findViewById(R.id.cli_name);
            cli_id = itemView.findViewById(R.id.cli_id);
//            cli_dob = itemView.findViewById(R.id.cli_dob);
            cli_phno = itemView.findViewById(R.id.cli_mobno);
        }
    }
}
