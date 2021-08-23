package search.finder.searchmovies.view.contentprovider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import search.finder.searchmovies.R

class ContactsAdapter () :
    RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {

    private lateinit var contactsData: List<Contact>

    fun setContacts(list: List<Contact>) {
        contactsData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.init(contactsData[position])
    }

    override fun getItemCount() = contactsData.size


    inner class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(contact: Contact) {
            with(itemView) {
                findViewById<TextView>(R.id.contact_name).text = contact.name
                findViewById<TextView>(R.id.contact_phone).text = contact.number
            }
        }
    }
}