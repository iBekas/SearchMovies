package search.finder.searchmovies.view.contentprovider

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import search.finder.searchmovies.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding: FragmentContactsBinding
        get() :FragmentContactsBinding = _binding!!
    private val contactsAdapter: ContactsAdapter = ContactsAdapter()
    private val contacts: MutableList<Contact> = ArrayList(10)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        binding.rvContacts.adapter = contactsAdapter
        contactsAdapter.setContacts(contacts)
    }

    private fun checkPermission() {
        context?.let {
            when {
                (ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                ) == PermissionChecker.PERMISSION_GRANTED)
                -> {
                    getContacts()
                }

                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)
                -> {
                    showRatio(it)
                }

                else -> requestPermission()
            }
        }
    }

    private fun showRatio(it: Context) {
        AlertDialog.Builder(it)
            .setTitle("Необходим доступ к контактам")
            .setMessage("Без доступа, вы не сможете позвонить Джони Деппу")
            .setPositiveButton("Предоставить доступ") { _, _ ->
                requestPermission()
            }
            .setNegativeButton("Отказать") { dialog, _ ->
                dialog.dismiss()
                requireActivity().finish()
            }
            .create()
            .show()
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_CONTACTS),
            REQUEST_CODE_FOR_CONTACTS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_FOR_CONTACTS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    context?.let { showRatio(it) }
                }
            }
        }
    }

    private fun getContacts() {
        context?.let { context: Context ->
            val contentResolver = context.contentResolver
            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursor?.let { cursor: Cursor ->
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        val number =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        contacts.add(Contact(name, number))
                    }
                }
            }
            cursor?.close()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val REQUEST_CODE_FOR_CONTACTS = 1
        fun newInstance() = ContactsFragment()
    }

}