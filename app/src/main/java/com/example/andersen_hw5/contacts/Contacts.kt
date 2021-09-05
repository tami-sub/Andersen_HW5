package com.example.andersen_hw5.contacts

open class Contacts {
    private val contacts = mutableMapOf<Int, Contact>(
        1 to Contact("Joka", "Boka", "89882431686"),
        2 to Contact("Biba", "Boba", "88005553535"),
        3 to Contact("Pupa", "Lupa", "89004076723")
    )
    fun getContacts(): MutableMap<Int, Contact> {
        return contacts
    }
    fun replace(id:Int, contact: Contact){
        contacts[id] = contact
    }
}
