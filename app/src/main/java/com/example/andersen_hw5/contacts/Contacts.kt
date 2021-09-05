package com.example.andersen_hw5.contacts

import android.os.Parcel
import android.os.Parcelable

open class Contacts() : Parcelable {
    private val contacts = mutableMapOf<Int, Contact>(
        1 to Contact("Joka", "Boka", "89882431686"),
        2 to Contact("Biba", "Boba", "88005553535"),
        3 to Contact("Pupa", "Lupa", "89004076723")
    )

    constructor(parcel: Parcel) : this() {
    }

    fun getContacts(): MutableMap<Int, Contact> {
        return contacts
    }

    fun replace(id: Int, contact: Contact) {
        contacts[id] = contact
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contacts> {
        override fun createFromParcel(parcel: Parcel): Contacts {
            return Contacts(parcel)
        }

        override fun newArray(size: Int): Array<Contacts?> {
            return arrayOfNulls(size)
        }
    }
}
