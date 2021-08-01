package search.finder.searchmovies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import search.finder.searchmovies.R
import search.finder.searchmovies.model.Movie

class UpcomingFragmentAdapter(var onItemViewClickListener: OnItemViewClickListener?): RecyclerView.Adapter<UpcomingFragmentAdapter.UpcomingHolder>() {

    private lateinit var moviesData: List<Movie>

    fun setMovies(list: List<Movie>){
        moviesData = list
        notifyDataSetChanged()
    }

    fun removeListener(){
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming, parent,false)
        return UpcomingHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        holder.init(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    inner class UpcomingHolder(view: View): RecyclerView.ViewHolder(view){
        fun init(movie: Movie){
//            itemView.findViewById<AppCompatImageView>(R.id.movie_img)
            itemView.findViewById<TextView>(R.id.movie_title_upcoming).text = movie.title
            itemView.findViewById<TextView>(R.id.movie_expect_data_upcoming).text = movie.expectData.toString()
            itemView.setOnClickListener{onItemViewClickListener?.onItemViewClick(movie)}
        }
    }
}