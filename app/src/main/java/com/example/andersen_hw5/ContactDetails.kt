package com.example.andersen_hw5

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentResultListener
import com.example.andersen_hw5.contacts.Contact
import com.example.andersen_hw5.contacts.Contacts
import com.example.andersen_hw5.databinding.FragmentContactDetailsBinding

class ContactDetails : Fragment() {

    private lateinit var binding:FragmentContactDetailsBinding
    private val contacts = Contacts()
    private var dataId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().replace(R.id.flFragment,
                    ContactListFragment.newInstance()).commit()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)

        parentFragmentManager.setFragmentResultListener("dataFrag1", this, FragmentResultListener { requestKey, result ->
            dataId = result.getString("data")?.toInt()!!
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
        binding.btnSave.setOnClickListener(View.OnClickListener {
            val name:String
            val surname:String
            val number:String
            with(binding){
                name = detailName.text.toString()
                surname = detailSurname.text.toString()
                number = detailNumber.text.toString()
                contacts.replace(dataId,Contact(name, surname, number))
            }
        })
    }
    companion object {
        fun newInstance() = ContactDetails()
    }
}