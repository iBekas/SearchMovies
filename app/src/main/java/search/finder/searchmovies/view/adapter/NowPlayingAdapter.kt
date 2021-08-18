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
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.TMDB_MOVIE_POSTER_URL

class NowPlayingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {

    private lateinit var moviesData: List<MovieDTO>

    fun setMovies(list: List<MovieDTO>) {
        moviesData = list
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing, parent, false)
        return NowPlayingHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        val positionInList: Int = position % moviesData.size
        holder.init(moviesData[positionInList])
    }

    override fun getItemCount() =  Integer.MAX_VALUE


    inner class NowPlayingHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: MovieDTO) {
            with(itemView) {
                with(movie) {
                    if(isAdult) movie_adult_img.visibility =  View.VISIBLE
                    findViewById<AppCompatImageView>(R.id.movie_img).load(TMDB_MOVIE_POSTER_URL+poster_path)
                    findViewById<TextView>(R.id.movie_title).text = title
                    findViewById<TextView>(R.id.movie_release_year).text =
                        release_date.substring(0, 4)
                    findViewById<TextView>(R.id.movie_votes_average).text =
                        vote_average.toString()
                    setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
                }
            }
        }
    }
}