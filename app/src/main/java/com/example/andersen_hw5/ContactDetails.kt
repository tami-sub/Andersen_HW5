package com.example.andersen_hw5

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import com.example.andersen_hw5.contacts.Contacts
import com.example.andersen_hw5.databinding.FragmentContactDetailsBinding

class ContactDetails : Fragment() {

    private lateinit var binding:FragmentContactDetailsBinding
    private val contacts = Contacts()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)
        // Inflate the layout for this fragment

        parentFragmentManager.setFragmentResultListener("dataFrag1", this, FragmentResultListener { requestKey, result ->
            val data = result.getString("data")?.toInt()
            val name = contacts.getContacts()[data]?.name
            val surname = contacts.getContacts()[data]?.surname
            val number = contacts.getContacts()[data]?.number
            with(binding) {
                detailName.setText(name)
                detailSurname.setText(surname)
                detailNumber.setText(number)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        fun newInstance() = ContactDetails()
    }
}