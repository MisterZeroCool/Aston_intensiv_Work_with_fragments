package ru.agavrilyuk.aston_intensiv_work_with_fragments.task_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import ru.agavrilyuk.aston_intensiv_work_with_fragments.R
import ru.agavrilyuk.aston_intensiv_work_with_fragments.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private var index = 0
    private var image = R.drawable.jen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.apply {
            image = this?.getInt(IMAGE) ?: R.drawable.jen
            val nameT = this?.getString(NAME)
            val lastNameT = this?.getString(LAST_NAME)
            val phoneT = this?.getString(PHONE)
            index = this?.getInt(INDEX) ?: 0
            binding.apply {
                name.setText(nameT)
                lastName.setText(lastNameT)
                phone.setText(phoneT)
                im.setImageResource(image)
            }
        }
        binding.back.setOnClickListener {
            val bundle = Bundle().apply {
                putInt(INDEX, index)
                putInt(IMAGE, image)
                putString(NAME, binding.name.text.toString())
                putString(LAST_NAME, binding.lastName.text.toString())
                putString(PHONE, binding.phone.text.toString())
            }
            setFragmentResult("requestKey", bundle)
            requireActivity().supportFragmentManager
                .popBackStack()
        }
        binding.im.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction().apply {
                    replace(R.id.host, SetIMageFragment())
                    addToBackStack("fIM")
                }.commit()
        }
        setFragmentResultListener("requestKey2"){ _, bundle ->
            image = bundle.getInt(IMAGE)
            binding.im.setImageResource(image)
        }
    }
}