package ru.agavrilyuk.aston_intensiv_work_with_fragments.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.agavrilyuk.aston_intensiv_work_with_fragments.R
import ru.agavrilyuk.aston_intensiv_work_with_fragments.databinding.FragmentMainScreenBinding
import ru.agavrilyuk.aston_intensiv_work_with_fragments.task_1.FragmentA
import ru.agavrilyuk.aston_intensiv_work_with_fragments.task_2.UserListFragment

class MainScreenFragment : Fragment() {
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            task1.setOnClickListener {
                requireActivity().supportFragmentManager
                    .beginTransaction().apply {
                        replace(R.id.host, FragmentA())
                        addToBackStack("fA")
                    }.commit()
            }
            task2.setOnClickListener {
                requireActivity().supportFragmentManager
                    .beginTransaction().apply {
                        replace(R.id.host, UserListFragment())
                        addToBackStack("fUM")
                    }.commit()
            }
        }
    }
}