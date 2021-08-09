package search.finder.searchmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import search.finder.searchmovies.R
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.NowPlayingDTO

class NowPlayingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {

    private lateinit var moviesData: NowPlayingDTO

    fun setMovies(list: NowPlayingDTO) {
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
        holder.init(moviesData.results[position])


    override fun getItemCount() = moviesData.results.size


    inner class NowPlayingHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: MovieDTO) {
            with(itemView) {
                with(movie) {
                    /*itemView.findViewById<AppCompatImageView>(R.id.movie_img)*/
                    findViewById<TextView>(R.id.movie_title).text = title
                    findViewById<TextView>(R.id.movie_release_year).text =
                        releaseDate
                    findViewById<TextView>(R.id.movie_votes_average).text =
                        voteAverage.toString()
                    setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
                }
            }
        }
    }
}