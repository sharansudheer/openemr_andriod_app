public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<AppointmentResponse> appointments;

    public AppointmentAdapter(List<AppointmentResponse> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        AppointmentResponse appointment = appointments.get(position);
        holder.pcEventDate.setText(appointment.getPcEventDate());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView pcEventDate;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            pcEventDate = itemView.findViewById(R.id.pc_eventDate);
        }
    }
}
//main code

RecyclerView recyclerView = findViewById(R.id.my_recyclerview);
GridLayoutManager layoutManager = new GridLayoutManager(this, 2); // Change the second parameter to the number of columns you want
recyclerView.setLayoutManager(layoutManager);

// Initialize the adapter with the list of AppointmentResponse objects
AppointmentAdapter appointmentAdapter = new AppointmentAdapter(appointments);
recyclerView.setAdapter(appointmentAdapter);
