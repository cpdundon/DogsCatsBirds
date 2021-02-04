package com.example.dogwebsite.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogwebsite.databinding.ItemImageBinding;

import java.util.List;

// TODO: 2/3/2021 Create adapter to take a List<Person>/**
// * Step 1: Extend RecyclerView.Adapter<>
// * Step 2: Create custom ViewHolder class and extend RecyclerView.ViewHolder
// * Step 3: Create constructor matching super [use Alt + Enter] for our CustomViewHolder
// * Step 4: Pass custom ViewHolder into the RecyclerView.Adapter<PASS_IT_HERE>
// * Step 5: Implement the Adapter methods: onCreateViewHolder, onBindViewHolder, getItemCount [use Alt + Enter]
// * Step 6: Pass list to the Adapter using Constructor or any other way
// * Step 7: Pass the list size to getItemCount as the return value
// * Step 8: setup onCreateViewHolder by inflating the layout using LayoutInflater or ViewBinding
// * Ex: LayoutInflater.from(parent.getContext()).inflate(R.layout.my_layout_name, parent, false);
// * Ex: MyItemBinding.inflate(layoutInflater, parent, false);
// * Step 9: Finish onCreateViewHolder setup by passing the inflated view to the customViewHolder class and returning it
// * Step 10: Setup views in your custom view holder class
// * Step 11 Setup onBindViewHolder, retrieve the item from list using the provided position and
// * use it to load the ItemView
// */
public class ShibeAdapter extends RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder> {
    private final List<String> shibes;

    public ShibeAdapter(List<String> shibes) {
        this.shibes = shibes;
    }

    @NonNull
    @Override
    public ShibeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageBinding binding = ItemImageBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ShibeViewHolder(binding);
    }

    @Override
    public int getItemCount() { return shibes.size(); }

    @Override
    public void onBindViewHolder(@NonNull ShibeViewHolder holder, int position) {
        String shibe = shibes.get(position);
        holder.setShibe(shibe);
    }

    static class ShibeViewHolder extends RecyclerView.ViewHolder {

        private final ItemImageBinding binding;

        public ShibeViewHolder(@NonNull ItemImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setShibe(String shibe) {
            ImageView imgShibe = binding.ivImage;
            String url = constructURL(shibe);
            Glide.with(this.itemView).load(url).into(imgShibe);
        }

        private String constructURL(String rootString) {
            final String prefix = "https://cdn.shibe.online/shibes/";
            final String suffix = ".jpg";
            return prefix + rootString + suffix;
        }

    }
}
