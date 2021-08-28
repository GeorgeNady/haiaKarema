package bee.bee.haiakarema.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bee.bee.haiakarema.databinding.ItemFileBinding
import bee.bee.haiakarema.model.DocumentFiles

class FilesAdapter:RecyclerView.Adapter<FilesAdapter.FileViewHolder>() {


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // ----------------------------------------------------------------------------------- Variables
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val TAG = this.javaClass.name

    private val differCallBack = object : DiffUtil.ItemCallback<DocumentFiles>() {
        override fun areItemsTheSame(oldItem: DocumentFiles, newItem: DocumentFiles) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DocumentFiles, newItem: DocumentFiles) =
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

            btnProject.text = current.name

            // go to data of one project activity
            btnProject.setOnClickListener {

            }
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