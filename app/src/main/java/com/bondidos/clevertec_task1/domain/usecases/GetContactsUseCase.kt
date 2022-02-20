package com.bondidos.clevertec_task1.domain.usecases

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import com.bondidos.clevertec_task1.domain.constants.Const.DISPLAY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.FAMILY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.GIVEN_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.PHONE_NUMBER
import com.bondidos.clevertec_task1.domain.constants.Const.PHOTO_URI
import com.bondidos.clevertec_task1.domain.model.ItemModel
import com.bondidos.clevertec_task1.domain.state.IsSuccess
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(private val context: Context) {

    fun execute(): IsSuccess {
        return try {
            val result = getContactList(context)
            IsSuccess.SuccessWithData(result)
        } catch (e: Exception) {
            IsSuccess.Error("Can't read contacts")
        }
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
        var namesMap: Map<String, String?>?
        var photoAndNumber: Map<String, String?>?

        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                //get contact id
                val idColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val id: String = cursor.getString(idColumnIndex)

                namesMap = getNames(context, id)

                photoAndNumber = getPhoneNumber(context, id)

                contactList.add(
                    ItemModel(
                        id = id,
                        name = namesMap,
                        number = photoAndNumber[PHONE_NUMBER],
                        email = getEmail(context, id),
                        image = photoAndNumber[PHOTO_URI]
                    )
                )
            }
        }
    }
    contentCursor?.close()
    return contactList
}

private fun getEmail(context: Context, id: String): String {

    val emailUri: Uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI

    val selectionEmail: String =
        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " =?"

    val emailCursor = context.contentResolver.query(
        emailUri,
        null,
        selectionEmail,
        arrayOf(id),
        null
    )

    var email = ""

    emailCursor?.let {
        if (emailCursor.moveToNext()) {
            val emailColumn = emailCursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Email.DATA
            )
            email = emailCursor.getString(emailColumn)
        }
    }

    emailCursor?.close()
    return email
}

private fun getPhoneNumber(context: Context, id: String): Map<String, String?> {

    val phoneUri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

    val selection: String =
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"

    val phoneCursor = context.contentResolver.query(
        phoneUri,
        null,
        selection,
        arrayOf(id),
        null
    )
    val result = mutableMapOf<String, String?>()

    phoneCursor?.let {
        if (phoneCursor.moveToNext()) {
            // phoneCursor move next
            val numberColumn = phoneCursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )
            val imageColumn = phoneCursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI
            )
            result[PHONE_NUMBER] = phoneCursor.getString(numberColumn)
            result[PHOTO_URI] = phoneCursor.getString(imageColumn)
        }
    }
    phoneCursor?.close()
    return result
}

private fun getNames(context: Context, id: String): Map<String, String?> {

    val whereName = ContactsContract.Data.MIMETYPE + " = ?"
    val whereNameParams = arrayOf(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
    val nameCur: Cursor? = context.contentResolver.query(
        ContactsContract.Data.CONTENT_URI,
        null,
        whereName,
        whereNameParams,
        ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME
    )
    val nameColumns = mutableMapOf<String, Int>()
    val names = mutableMapOf<String, String?>()

    nameCur?.let {
        nameColumns[GIVEN_NAME] =
            nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME)
        nameColumns[FAMILY_NAME] =
            nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME)
        nameColumns[DISPLAY_NAME] =
            nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME)

        try {
            nameCur.move(id.toInt())
            names[GIVEN_NAME] = nameColumns[GIVEN_NAME]?.let { it1 -> nameCur.getString(it1) }
            names[FAMILY_NAME] = nameColumns[FAMILY_NAME]?.let { it1 -> nameCur.getString(it1) }
            names[DISPLAY_NAME] = nameColumns[DISPLAY_NAME]?.let { it1 -> nameCur.getString(it1) }
        } catch (e: IndexOutOfBoundsException) {
            nameCur.close()
            return emptyMap()
        }

    }

    nameCur?.close()
    return names
}