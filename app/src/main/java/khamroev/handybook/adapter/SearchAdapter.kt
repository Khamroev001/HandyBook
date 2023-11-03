package khamroev.handybook.adapter




import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import khamroev.handybook.R
import khamroev.handybook.model.Book
import khamroev.handybook.utils.SharedPrefHelper


class SearchAdapter(var books : List<Book>,
                   val bookClicked: BookClicked
): RecyclerView.Adapter<SearchAdapter.BookHolder>() {
    val saved = MutableList(books.size){false}
    inner class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.search_item_book_name)
        val author:TextView = itemView.findViewById(R.id.search_item_book_author)
        val rating:TextView = itemView.findViewById(R.id.search_item_book_rating)
        val imageIV:ImageView = itemView.findViewById(R.id.search_item_image)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        return BookHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_book_item, parent, false))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val book = books[position]

        holder.name.text=book.name
        holder.author.text = book.author
        holder.imageIV.load(book.image)
        holder.rating.text = book.reyting.toString()

        }


    }

    interface BookClicked{
        fun onClicked(book:Book)
    }

