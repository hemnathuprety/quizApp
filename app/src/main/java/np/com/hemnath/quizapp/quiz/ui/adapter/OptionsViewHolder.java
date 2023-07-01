package np.com.hemnath.quizapp.quiz.ui.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import np.com.hemnath.quizapp.R;
import np.com.hemnath.quizapp.databinding.AdapterOptionItemBinding;
import np.com.hemnath.quizapp.quiz.presentation.model.OptionModel;

class OptionsViewHolder extends RecyclerView.ViewHolder {
    AdapterOptionItemBinding binding;

    public OptionsViewHolder(@NonNull AdapterOptionItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(OptionModel item) {
        binding.txtOptions.setText(item.getAnswers());

        int color;
        if (item.getSelected()) color = R.color.white;
        else color = R.color.colorPrimary;
        binding.txtOptions.setTextColor(ContextCompat.getColor(itemView.getContext(), color));

        if (item.getSelected()) binding.layoutChecked.setVisibility(View.VISIBLE);
        else binding.layoutChecked.setVisibility(View.GONE);
    }
}