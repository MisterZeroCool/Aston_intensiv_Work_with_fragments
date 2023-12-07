package ru.agavrilyuk.aston_intensiv_work_with_fragments.task_2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import ru.agavrilyuk.aston_intensiv_work_with_fragments.R
import ru.agavrilyuk.aston_intensiv_work_with_fragments.databinding.FragmentUserListBinding

const val INDEX = "index"
const val NAME = "name"
const val LAST_NAME = "last_name"
const val PHONE = "phone"
const val IMAGE = "image"

class UserListFragment : Fragment(), UsersAdapter.Listener {
    val fakeList = listOf(
        User(
            0,
            R.drawable.pavel,
            "Pavel",
            "Nesterenko",
            "88002000600"
        ),
        User(
            1,
            R.drawable.roca,
            "Dwayne",
            "Johnson",
            "6666666"
        ),
        User(
            2,
            R.drawable.jp,
            "Ryan",
            "Gosling",
            "7777777"
        ),
        User(
            3,
            R.drawable.jen,
            "Jennifer ",
            "Lawrence",
            "8888888"
        ),
    )
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var preferences: SharedPreferences
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = requireActivity().getSharedPreferences(
            "main", Context.MODE_PRIVATE
        )
        init()
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val index = bundle.getInt(INDEX)
            val image = bundle.getInt(IMAGE)
            val name = bundle.getString(NAME)
            val lastName = bundle.getString(LAST_NAME)
            val phone = bundle.getString(PHONE)
            preferences.edit().putString(NAME + index, name)
                .putString(LAST_NAME + index, lastName)
                .putString(PHONE + index, phone)
                .putBoolean(index.toString(), true)
                .putInt(IMAGE + index, image)
                .apply()
            usersAdapter.submitList(getUsers())
        }
    }

    private fun init() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(requireContext())
        usersAdapter = UsersAdapter(this@UserListFragment)
        rcView.adapter = usersAdapter
        usersAdapter.submitList(getUsers())
    }

    private fun getUsers(): List<User> {
        val userList = ArrayList<User>()
        for (i in fakeList.indices) {
            userList.add(
                if (!preferences.getBoolean(i.toString(), false)) {
                    fakeList[i]
                } else {
                    User(
                        i,
                        preferences.getInt(IMAGE + i, R.drawable.jen),
                        preferences.getString(NAME + i, "") ?: "",
                        preferences.getString(LAST_NAME + i, "") ?: "",
                        preferences.getString(PHONE + i, "") ?: ""
                    )
                }
            )
        }
        return userList
    }

    override fun onClick(user: User) {
        requireActivity().supportFragmentManager
            .beginTransaction().apply {

                val bundle = Bundle().apply {
                    putInt(INDEX, user.id)
                    putInt(IMAGE, user.im)
                    putString(NAME, user.name)
                    putString(LAST_NAME, user.lastName)
                    putString(PHONE, user.phoneNumber)
                }
                replace(R.id.host, EditFragment().apply {
                    arguments = bundle
                })
                addToBackStack("fED")
            }.commit()
    }
}