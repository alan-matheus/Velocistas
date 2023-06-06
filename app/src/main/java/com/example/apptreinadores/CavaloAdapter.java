package com.example.apptreinadores;

public class CavaloAdapter {
    private List<Horse> horseList;

    public HorseAdapter(List<Horse> horseList) {
        this.horseList = horseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horse, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Horse horse = horseList.get(position);
        holder.textViewName.setText(horse.getName());
        holder.textViewBreed.setText(horse.getBreed());
        holder.textViewArrivalDate.setText(horse.getArrivalDate());
    }

    @Override
    public int getItemCount() {
        return horseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewBreed;
        private TextView textViewArrivalDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewBreed = itemView.findViewById(R.id.textViewBreed);
            textViewArrivalDate = itemView.findViewById(R.id.textViewArrivalDate);
        }
    }

}
