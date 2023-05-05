package hos_app.recyclerview_Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hos_app.main_application.R;

public class AllergyAdapter extends RecyclerView.Adapter<AllergyAdapter.AllergyViewHolder> {
    private List<Allergy> allergyList;

    public AllergyAdapter(List<Allergy> allergyList) {
        this.allergyList = allergyList;
    }

    @NonNull
    @Override
    public AllergyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allergy_card_item, parent, false);
        return new AllergyViewHolder(itemView);
    }
    public void updateAllergyList(List<Allergy> newAllergyList) {
        this.allergyList = newAllergyList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AllergyViewHolder holder, int position) {
        Allergy allergy = allergyList.get(position);
        holder.date.setText("Date: " + allergy.getDate());
        holder.allergy.setText("Allergies: " + allergy.getAllergy());
        holder.practitioner.setText("Doctor: " + allergy.getPractitioner());
    }

    @Override
    public int getItemCount() {
        return allergyList.size();
    }

    public static class AllergyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView allergy;
        TextView practitioner;

        public AllergyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.allergy_date);
            allergy = itemView.findViewById(R.id.allergy_name);
            practitioner = itemView.findViewById(R.id.allergy_practitioner);
        }
    }
}

