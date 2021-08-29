package bee.bee.haiakarema.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import bee.bee.haiakarema.R
import bee.bee.haiakarema.adapters.FilesAdapter
import bee.bee.haiakarema.base.ActivityFragmentAnnoation
import bee.bee.haiakarema.base.BaseFragment
import bee.bee.haiakarema.databinding.FragmentFilesBinding
import bee.bee.haiakarema.model.DocumentFiles
import bee.bee.haiakarema.model.ItemProject
import bee.bee.haiakarema.viewmodel.fragments.AuthViewModel
import bee.bee.haiakarema.viewmodel.fragments.ProjectsViewModel

@SuppressLint("NonConstantResourceId")
@ActivityFragmentAnnoation(R.layout.fragment_files)
class FilesFragment : BaseFragment<FragmentFilesBinding>() {

    private lateinit var viewModel: ProjectsViewModel

    lateinit var filesAdapter:FilesAdapter

    override val TAG: String
        get() = this.javaClass.name

    override fun initViewModel() {
        viewModel = ViewModelProvider(this@FilesFragment).get(ProjectsViewModel::class.java)

    }

    override fun setListener() {
        filesAdapter= FilesAdapter(this@FilesFragment)
        binding?.apply {
            viewModel.getProjects(progress).observe(this@FilesFragment,{
                filesAdapter.differ.submitList(it)
                rvFiles.adapter=filesAdapter
                filesAdapter.notifyDataSetChanged()
            })
        }
    }

}