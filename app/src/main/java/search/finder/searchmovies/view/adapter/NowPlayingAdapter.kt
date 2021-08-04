package search.finder.searchmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import search.finder.searchmovies.R
import search.finder.searchmovies.model.Movie

class NowPlayingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {

    private lateinit var moviesData: List<Movie>

    fun setMovies(list: List<Movie>) {
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

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) =
        holder.init(moviesData[position])


    override fun getItemCount() = moviesData.size


    inner class NowPlayingHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: Movie) {
            with(itemView) {
                with(movie) {
                    /*itemView.findViewById<AppCompatImageView>(R.id.movie_img)*/
                    findViewById<TextView>(R.id.movie_title).text = title
                    findViewById<TextView>(R.id.movie_release_year).text =
                        releaseYear.toString()
                    findViewById<TextView>(R.id.movie_votes_average).text =
                        vote.toString()
                    setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
                }
            }
        }
    }
}