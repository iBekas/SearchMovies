package search.finder.searchmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import search.finder.searchmovies.R
import search.finder.searchmovies.model.Movie

class NowPlayingFragmentAdapter(var onItemViewClickListener: OnItemViewClickListener?): RecyclerView.Adapter<NowPlayingFragmentAdapter.NowPlayingHolder>() {

    private lateinit var moviesData: List<Movie>

    fun setMovies(list: List<Movie>){
        moviesData = list
        notifyDataSetChanged()
    }

    fun removeListener(){
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing, parent,false)
        return NowPlayingHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        holder.init(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    inner class NowPlayingHolder(view: View): RecyclerView.ViewHolder(view){
        fun init(movie: Movie){
//            itemView.findViewById<AppCompatImageView>(R.id.movie_img)
            itemView.findViewById<TextView>(R.id.movie_title).text = movie.title
            itemView.findViewById<TextView>(R.id.movie_release_year).text = movie.releaseYear.toString()
            itemView.findViewById<TextView>(R.id.movie_votes_average).text = movie.vote.toString()
            itemView.setOnClickListener{onItemViewClickListener?.onItemViewClick(movie)}
        }
    }
}