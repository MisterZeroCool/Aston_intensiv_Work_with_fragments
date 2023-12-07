package ru.agavrilyuk.aston_intensiv_work_with_fragments.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.agavrilyuk.aston_intensiv_work_with_fragments.R
import ru.agavrilyuk.aston_intensiv_work_with_fragments.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding
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
            next.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("message", "Hello Fragment C!")
                }
                requireActivity().supportFragmentManager
                    .beginTransaction().apply {
                        replace(R.id.host, FragmentC().apply {
                            arguments = bundle
                        })
                        addToBackStack("fC")
                    }.commit()

            }
            back.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}