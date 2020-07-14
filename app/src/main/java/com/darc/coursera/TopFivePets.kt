package com.darc.coursera

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darc.coursera.adapter.PetAdapter
import com.darc.coursera.database.entities.Pet
import com.darc.coursera.database.repository.PetRepository
import com.darc.coursera.databinding.ActivityTopFivePetsBinding

class TopFivePets : AppCompatActivity(),TopFivePetsVM.Listener {

    companion object {
        fun startActivity(context: Context){
            val intent = Intent(context,TopFivePets::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var binding : ActivityTopFivePetsBinding

    lateinit var viewModel : TopFivePetsVM

    lateinit var adapter : PetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this,R.layout.activity_top_five_pets)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TopFivePetsVM::class.java)
        viewModel.listener = this

        binding.viewModel = viewModel

        adapter = PetAdapter(arrayListOf())

        adapter.like = {
            viewModel.update(it.apply { likes += 1 })
        }

        binding.rvPets.adapter = adapter

        viewModel.getPetsTopFive().observe(this, Observer {
            adapter.changeList(ArrayList(it))
        })


    }

    override fun clckBack() {
        finish()
    }
}

class TopFivePetsVM(application: Application) : AndroidViewModel(application){

    private var coordRepository : PetRepository = PetRepository(application)

    var listener : Listener? = null

    fun getPetsTopFive() : LiveData<List<Pet>> {
        return coordRepository.getPetsTopFive()
    }

    fun update(pet : Pet)  {
        coordRepository.update(pet)
    }

    interface Listener {
        fun clckBack()
    }
}