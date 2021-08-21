package search.finder.searchmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_now_playing.view.*
import search.finder.searchmovies.R
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.model.TMDB_MOVIE_POSTER_URL

class HistoryAdapter(var onHistoryItemClick: OnHistoryItemClick?) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    private lateinit var moviesData: List<MovieDetailsDTO>

    fun setMovies(list: List<MovieDetailsDTO>) {
        moviesData = list
        notifyDataSetChanged()
    }

    fun removeListener() {
        onHistoryItemClick = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.init(moviesData[position])
    }

    override fun getItemCount() =  moviesData.size


    inner class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: MovieDetailsDTO) {
            with(itemView) {
                with(movie) {
                    if(isAdult) movie_adult_img.visibility =  View.VISIBLE
                    findViewById<AppCompatImageView>(R.id.movie_img).load(TMDB_MOVIE_POSTER_URL +poster_path)
                    findViewById<TextView>(R.id.movie_title).text = title
                    findViewById<TextView>(R.id.movie_release_year).text =
                        release_date.substring(0, 4)
                    findViewById<TextView>(R.id.movie_votes_average).text =
                        vote_average.toString()
                    setOnClickListener { onHistoryItemClick?.onItemHistoryClick(movie) }
                }
            }
        }
    }
}