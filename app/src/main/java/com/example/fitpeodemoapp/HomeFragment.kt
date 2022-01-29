package com.example.fitpeodemoapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpeodemoapp.adapters.AvatarsAdapter
import com.example.fitpeodemoapp.adapters.AvatarsFutureAdapter
import com.example.fitpeodemoapp.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    private lateinit var _binding:FragmentHomeBinding
    val binding:FragmentHomeBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val bottomNav=activity?.findViewById<BottomNavigationView>(com.example.fitpeodemoapp.R.id.bottom_navigation)
        bottomNav?.visibility=View.VISIBLE


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAvatars.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.attendees2RecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val imagesList = listOf(
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar1),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar2),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar3),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar4),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar5),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar6)
        )

        val avatarsList= listOf(ContextCompat.getDrawable(requireContext(), R.drawable.avatar1),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar2),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar3),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar4),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar5),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar6),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar_female),
            ContextCompat.getDrawable(requireContext(), R.drawable.avatar_7))

        binding.recyclerViewAvatars.adapter = AvatarsAdapter(imagesList)
        binding.attendees2RecyclerView.adapter=AvatarsFutureAdapter(avatarsList)

        binding.infoButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_passDetailsFragment)
        }

    }


}