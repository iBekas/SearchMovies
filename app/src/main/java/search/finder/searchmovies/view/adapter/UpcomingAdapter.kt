package search.finder.searchmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_upcoming.view.*
import search.finder.searchmovies.R
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.TMDB_MOVIE_POSTER_URL

class UpcomingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<UpcomingAdapter.UpcomingHolder>() {

    private lateinit var moviesData: List<MovieDTO>

    fun setMovies(list: List<MovieDTO>) {
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

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        val positionInList: Int = position % moviesData.size
        holder.init(moviesData[positionInList])
    }

    override fun getItemCount() = Integer.MAX_VALUE

    inner class UpcomingHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: MovieDTO) {
            with(itemView) {
                with(movie) {
                    if (isAdult) movie_adult_img_upcoming.visibility = View.VISIBLE
                    itemView.findViewById<AppCompatImageView>(R.id.movie_img_upcoming).load(
                        TMDB_MOVIE_POSTER_URL + poster_path
                    )
                    findViewById<TextView>(R.id.movie_title_upcoming).text = title
                    findViewById<TextView>(R.id.movie_expect_data_upcoming).text =
                        release_date
                    setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
                }
            }
        }
    }
}