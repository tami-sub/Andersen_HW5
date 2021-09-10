package com.example.andersen_hw5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.andersen_hw5.contacts.Contact
import com.example.andersen_hw5.contacts.Contacts
import com.example.andersen_hw5.databinding.FragmentContactDetailsBinding

class ContactDetails : Fragment() {

    private lateinit var binding: FragmentContactDetailsBinding
    private var contacts: Contacts = Contacts()
    private var dataId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                sendToMainFragment(contacts)
                parentFragmentManager.beginTransaction().replace(R.id.flFragment,
                    ContactListFragment.newInstance()).commit()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContactDetailsBinding.inflate(inflater)

        parentFragmentManager.setFragmentResultListener("dataFrag1",
            this,
            { _, result ->
                dataId = result.getString("data")?.toInt()!!
                contacts = result.getParcelable("allContacts")!!
                val name = contacts.getContacts()[dataId]?.name
                val surname = contacts.getContacts()[dataId]?.surname
                val number = contacts.getContacts()[dataId]?.number
                with(binding) {
                    detailName.setText(name)
                    detailSurname.setText(surname)
                    detailNumber.setText(number)
                }
            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener {
            val name: String
            val surname: String
            val number: String

            with(binding) {
                name = detailName.text.toString()
                surname = detailSurname.text.toString()
                number = detailNumber.text.toString()
                contacts.replace(dataId, Contact(name, surname, number))
            }

            sendToMainFragment(contacts)

            if (resources.configuration?.smallestScreenWidthDp!! < 600) {
            parentFragmentManager.beginTransaction().replace(R.id.flFragment,
                ContactListFragment.newInstance()).commit()
            }
        }
    }

    private fun sendToMainFragment(contacts:Contacts) {
        val result = Bundle()
        result.putParcelable("getAllContacts", contacts)
        parentFragmentManager.setFragmentResult("dataFrag2", result)
    }

    companion object {
        fun newInstance() = ContactDetails()
    }
}