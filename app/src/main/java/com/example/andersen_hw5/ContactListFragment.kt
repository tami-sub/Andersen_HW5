package com.example.andersen_hw5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.andersen_hw5.contacts.Contact
import com.example.andersen_hw5.contacts.Contacts
import com.example.andersen_hw5.databinding.ContactListFragmentBinding

class ContactListFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: ContactListFragmentBinding
    private var contacts = Contacts()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = ContactListFragmentBinding.inflate(inflater)
        initAllContacts()
        parentFragmentManager.setFragmentResultListener("dataFrag2", this, { _, result ->
            contacts = result.getParcelable("getAllContacts")!!
            initAllContacts()
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            contactHandle(contact1)
            contactHandle(contact2)
            contactHandle(contact3)
        }
    }

    companion object {
        fun newInstance() = ContactListFragment()
    }

    override fun onClick(p0: View?) {
        val result = Bundle()
        val data = p0?.let { resources.getResourceEntryName(it.id) }
        if (data != null) {
            result.putString("data", data[data.lastIndex].toString())
            result.putParcelable("allContacts", contacts)
        }

        if (resources.configuration?.smallestScreenWidthDp!! >= 600) {
            parentFragmentManager.setFragmentResult("dataFrag1", result)
            parentFragmentManager.beginTransaction().replace(R.id.flFragment2,
                ContactDetails.newInstance()).commit()
        } else {
            parentFragmentManager.setFragmentResult("dataFrag1", result)
            parentFragmentManager.beginTransaction().replace(R.id.flFragment,
                ContactDetails.newInstance()).commit()
        }
    }

    private fun contactHandle(contact: LinearLayout) {
        contact.setOnClickListener(this)
    }

    private fun initAllContacts() {
        for (currentContact in contacts.getContacts()) {
            initContactInfo(currentContact.key, currentContact.value)
        }
    }

    private fun initContactInfo(number: Int, contact: Contact) {
        with(binding) {
            when (number) {
                1 -> {
                    contact1BlockName.text = contact.name
                    contact1BlockSurname.text = contact.surname
                    contact1BlockNumber.text = contact.number
                }
                2 -> {
                    contact2BlockName.text = contact.name
                    contact2BlockSurname.text = contact.surname
                    contact2BlockNumber.text = contact.number
                }
                3 -> {
                    contact3BlockName.text = contact.name
                    contact3BlockSurname.text = contact.surname
                    contact3BlockNumber.text = contact.number
                }
            }
        }
    }
}