package search.finder.searchmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import search.finder.searchmovies.R
import search.finder.searchmovies.model.MovieDTO

class UpcomingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<UpcomingAdapter.UpcomingHolder>() {

    private lateinit var moviesData: ArrayList<MovieDTO>

    fun setMovies(list: ArrayList<MovieDTO>) {
        moviesData = list
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming, parent, false)
        return UpcomingHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) =
        holder.init(moviesData[position])

    override fun getItemCount() = moviesData.size

    inner class UpcomingHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: MovieDTO) {
            with(itemView) {
                with(movie) {
                    /*itemView.findViewById<AppCompatImageView>(R.id.movie_img)*/
                    findViewById<TextView>(R.id.movie_title_upcoming).text = title
                    findViewById<TextView>(R.id.movie_expect_data_upcoming).text =
                        release_date
                    setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
                }
            }
        }
    }
}