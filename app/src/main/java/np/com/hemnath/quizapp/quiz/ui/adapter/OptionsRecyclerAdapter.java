package np.com.hemnath.quizapp.quiz.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import np.com.hemnath.quizapp.databinding.AdapterOptionItemBinding;
import np.com.hemnath.quizapp.quiz.presentation.model.OptionModel;

public class OptionsRecyclerAdapter extends RecyclerView.Adapter<OptionsViewHolder> {

    private final AdapterListener listener;
    private final List<OptionModel> mutableList;

    public OptionsRecyclerAdapter(List<OptionModel> mutableList, AdapterListener listener) {
        this.listener = listener;
        this.mutableList = mutableList;
    }

    @NonNull
    @Override
    public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OptionsViewHolder(AdapterOptionItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {
        OptionModel item = mutableList.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(view -> listener.onClick(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return mutableList.size();
    }
}