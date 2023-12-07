package ru.agavrilyuk.aston_intensiv_work_with_fragments.task_1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.agavrilyuk.aston_intensiv_work_with_fragments.R
import ru.agavrilyuk.aston_intensiv_work_with_fragments.databinding.FragmentCBinding

class FragmentC : Fragment() {
    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog", "Fragment c")
        binding.apply {
            arguments.apply {
                title.text = this?.getString("message") ?: ""
            }
            next.setOnClickListener {
                requireActivity().supportFragmentManager.apply {
                    beginTransaction().apply {
                        replace(R.id.host, FragmentD())
                        addToBackStack("fD")
                    }.commit()
                }
            }
            back.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack(
                    "fA", 0
                )
            }
        }
    }
}