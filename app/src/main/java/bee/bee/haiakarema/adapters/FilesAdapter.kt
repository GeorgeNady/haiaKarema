package bee.bee.haiakarema.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bee.bee.haiakarema.databinding.ItemFileBinding

class FilesAdapter:RecyclerView.Adapter<FilesAdapter.FileViewHolder>() {


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
    inner class FileViewHolder(val binding: ItemFileBinding) :
        RecyclerView.ViewHolder(binding.root)


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ---------------------------------------------------------- Recycler View Members to Implement
    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FileViewHolder(ItemFileBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount() = differ.currentList.size


    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {

        val current = differ.currentList[position]

        holder.binding.apply {



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