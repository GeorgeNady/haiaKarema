package bee.bee.haiakarema.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bee.bee.haiakarema.databinding.ItemVideoBinding
import bee.bee.haiakarema.ui.main.fragments.ProjectFragment
import com.nostra13.universalimageloader.core.ImageLoader

class VideoAdapter(val context: ProjectFragment):RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // ----------------------------------------------------------------------------------- Variables
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val TAG = this.javaClass.name

    private val differCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem.length == newItem.length

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
    val differ = AsyncListDiffer(this, differCallBack)


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // --------------------------------------------------------------------------------- View Holder
    ////////////////////////////////////////////////////////////////////////////////////////////////
    inner class VideoViewHolder(val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root)


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ---------------------------------------------------------- Recycler View Members to Implement
    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoViewHolder(ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount() = differ.currentList.size


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        val current = differ.currentList[position]

        holder.binding.apply {

            ImageLoader.getInstance().displayImage(current, videoView.ivThumb)
            videoView.setUp(current, "")

        }


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ---------------------------------------------------------------------------- Helper Functions
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

}