package bee.bee.haiakarema.adapters

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bee.bee.haiakarema.R
import bee.bee.haiakarema.databinding.ItemFileBinding
import bee.bee.haiakarema.databinding.ItemPdfBinding
import bee.bee.haiakarema.model.DocumentFiles
import bee.bee.haiakarema.model.ItemProject
import bee.bee.haiakarema.ui.main.fragments.FilesFragment
import bee.bee.haiakarema.ui.main.fragments.ProjectFragment

class PdfAdapter(val context: ProjectFragment) : RecyclerView.Adapter<PdfAdapter.PdfViewHolder>() {


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
    inner class PdfViewHolder(val binding: ItemPdfBinding) :
        RecyclerView.ViewHolder(binding.root)


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ---------------------------------------------------------- Recycler View Members to Implement
    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PdfViewHolder(
            ItemPdfBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = differ.currentList.size


    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {

        val current = differ.currentList[position]

        holder.binding.apply {

            // TODO : LOGIN HERE
            "PDF ${position + 1}".also { btnProject.text = it }
            root.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(current))
                context.startActivity(browserIntent)
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