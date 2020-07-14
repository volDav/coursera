package com.darc.coursera.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.darc.coursera.R
import com.darc.coursera.adapter.PetAdapter
import com.darc.coursera.database.repository.PetRepository
import com.darc.coursera.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    companion object {
        val PAGER_POSITION = 0

        fun newFragment(): ListFragment {
            val fragment = ListFragment()
            return fragment
        }
    }

    lateinit var binding : ListFragmentBinding

    lateinit var adapter : PetAdapter

    lateinit var coordRepository : PetRepository


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListFragmentBinding.bind(inflater.inflate(R.layout.list_fragment, container, false))

        activity?.application?.let {
            coordRepository = PetRepository(it)
        }

        adapter = PetAdapter(arrayListOf())


        adapter.like = {

            coordRepository.update(it.apply { likes += 1 })
        }

        binding.rvPets.adapter = adapter

        coordRepository.getPets().observe(viewLifecycleOwner, Observer {
            adapter.changeList(ArrayList(it))
        })

        return binding.root
    }
}
