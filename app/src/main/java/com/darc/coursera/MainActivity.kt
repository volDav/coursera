package com.darc.coursera

import android.app.Application
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.darc.coursera.adapter.PetAdapter
import com.darc.coursera.database.entities.Pet
import com.darc.coursera.database.repository.PetRepository
import com.darc.coursera.databinding.ActivityMainBinding
import com.darc.coursera.ktx.bindLazy
import kotlinx.android.synthetic.main.body_home_sesion.*

class MainActivity : AppCompatActivity(),MainActivityVM.Listener {


    private val toolbar: Toolbar by bindLazy(R.id.toolbar)

    lateinit var binding : ActivityMainBinding

    lateinit var viewModel  : MainActivityVM

    lateinit var adapter : PetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityVM::class.java)
        viewModel.listener = this

        binding.viewModel = viewModel

        adapter = PetAdapter(arrayListOf())

        adapter.like = {
            viewModel.update(it.apply { likes += 1 })
        }

        binding.rvPets.adapter = adapter

        viewModel.getPets().observe(this, Observer {
            adapter.changeList(ArrayList(it))
        })
    }

    override fun clckTopFive() {
        TopFivePets.startActivity(this)
    }
/*
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setHomeIndicator(R.drawable.ic_back_arrow)
    }

    private fun setHomeIndicator(@DrawableRes p0: Int) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val vectorDrawableCompat = VectorDrawableCompat.create(resources, p0, null)
        vectorDrawableCompat?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(vectorDrawableCompat)
    }
*/

}

class MainActivityVM(application: Application) : AndroidViewModel(application){

    private var coordRepository : PetRepository = PetRepository(application)

    var listener : Listener? = null

    fun getPets() : LiveData<List<Pet>> {
        return coordRepository.getPets()
    }

    fun update(pet : Pet)  {
        coordRepository.update(pet)
    }

    interface Listener {
        fun clckTopFive()
    }

}
