package com.bondidos.clevertec_task1

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bondidos.clevertec_task1.model.ItemModel
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(private val context: Context) {

    fun execute(): List<ItemModel> {
        checkPermissions(context)
        return getContactList(context)
    }
}

private fun getContactList(context: Context): List<ItemModel> {

    val contactList = mutableListOf<ItemModel>()

    // contacts uri
    val uri: Uri = ContactsContract.Contacts.CONTENT_URI
    // sort by asc
    val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
    // init cursor
    val contentCursor: Cursor? = context.contentResolver.query(
        uri,
        null,
        null,
        null,
        sort
    )
    // condition check
    contentCursor?.let { cursor ->
        var number: String? = null
        var email: String? = null


        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                //get contact id
                val idColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val id: String = cursor.getString(idColumnIndex)

                //get contact name
                val nameColumnIndex =
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
                val name = cursor.getString(nameColumnIndex)

                // init phone uri
                val phoneUri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

                // init selection
                val selection: String =
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"

                //init phoneCursor
                val phoneCursor = context.contentResolver.query(
                    phoneUri,
                    null,
                    selection,
                    arrayOf(id),
                    null
                )

                // check condition
                phoneCursor?.let {
                    if (phoneCursor.moveToNext()) {
                        // phoneCursor move next
                        val numberColumn = phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        number = phoneCursor.getString(numberColumn)
                    }
                }
                phoneCursor?.close()

                val emailUri: Uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI

                val selectionEmail: String =
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " =?"

                //init phoneCursor
                val emailCursor = context.contentResolver.query(
                    emailUri,
                    null,
                    selectionEmail,
                    arrayOf(id),
                    null
                )
                emailCursor?.let {
                    if (emailCursor.moveToNext()) {
                        // phoneCursor move next
                        val emailColumn = emailCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Email.DATA
                        )
                        email = emailCursor.getString(emailColumn)
                    }
                }

                emailCursor?.close()

                contactList.add(
                    ItemModel(
                        id = id,
                        name = name,
                        number = number ?: "",
                        email = email
                    )
                )
            }
        }
    }
    contentCursor?.close()
    return contactList
}
private fun checkPermissions(context: Context) {
    if(ContextCompat.checkSelfPermission(context,
            Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
        //request permission
        ActivityCompat.requestPermissions(
            context as MainActivity,
            arrayOf(Manifest.permission.READ_CONTACTS),
            100
        )
    } /*else {
        val contactList = getContactList(context)
        Log.d("Main",contactList.toString())
    }*/
}