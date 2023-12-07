package ru.agavrilyuk.aston_intensiv_work_with_fragments.task_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import ru.agavrilyuk.aston_intensiv_work_with_fragments.R
import ru.agavrilyuk.aston_intensiv_work_with_fragments.databinding.FragmentSetIMageBinding

class SetIMageFragment : Fragment() {
    private var _binding: FragmentSetIMageBinding? = null
    private val binding get() = _binding!!
    private var image = R.drawable.jen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetIMageBinding
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
            im1.setOnClickListener {
              setImage(R.drawable.pavel)
            }
            im2.setOnClickListener {
                setImage(R.drawable.jen)
            }
            im3.setOnClickListener {
                setImage(R.drawable.roca)
            }
            im4.setOnClickListener {
                setImage(R.drawable.jp)
            }
        }
    }
    private fun setImage(imageId: Int){
        val bundle = Bundle().apply {
            putInt(IMAGE, imageId)
        }
        setFragmentResult("requestKey2", bundle)
        requireActivity().supportFragmentManager
            .popBackStack()
    }
}
