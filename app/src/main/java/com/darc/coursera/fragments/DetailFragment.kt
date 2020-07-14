package com.darc.coursera.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.darc.coursera.R
import com.darc.coursera.adapter.PetAdapter
import com.darc.coursera.adapter.PetDetailAdapter
import com.darc.coursera.database.repository.PetRepository
import com.darc.coursera.databinding.DetailFragmentBinding
import com.darc.coursera.databinding.ListFragmentBinding

class DetailFragment : Fragment() {

    companion object {

        fun newFragment(): DetailFragment {
            return DetailFragment()
        }
    }

    lateinit var binding : DetailFragmentBinding

    lateinit var adapter : PetDetailAdapter

    lateinit var coordRepository : PetRepository


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DetailFragmentBinding.bind(inflater.inflate(R.layout.detail_fragment, container, false))

        activity?.application?.let {
            coordRepository = PetRepository(it)
        }

        coordRepository.getPets().observe(viewLifecycleOwner, Observer {
            adapter = PetDetailAdapter(ArrayList(it))
            binding.rvGrid.adapter = adapter
        })


        return binding.root
    }
}