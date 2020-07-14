package com.darc.coursera.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darc.coursera.R
import com.darc.coursera.database.entities.Pet
import com.darc.coursera.databinding.HolderPetBinding
import com.darc.coursera.databinding.HolderPetDetailBinding
import java.util.logging.Handler


class PetDetailAdapter(private var lst : ArrayList<Pet>) : RecyclerView.Adapter<PetDetailAdapter.HolderPet>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPet {
        val view  : View = LayoutInflater.from(parent.context).inflate(R.layout.holder_pet_detail,parent,false)
        return HolderPet(view)
    }

    override fun getItemCount(): Int = lst.size

    override fun onBindViewHolder(holder: HolderPet, position: Int) {
        holder.binding.pet = lst[position]

        holder.binding.img.setImageResource(
        when(lst[position].id.toInt()){
            1 -> R.drawable.img1
            2 -> R.drawable.img2
            3 -> R.drawable.img3
            4 -> R.drawable.img4
            5 -> R.drawable.img5
            6 -> R.drawable.img6
            7 -> R.drawable.img7
            8 -> R.drawable.img8
            else -> 0
        })

    }

    class HolderPet(view: View) : RecyclerView.ViewHolder(view) {
        val binding  = HolderPetDetailBinding.bind(view)
    }

}