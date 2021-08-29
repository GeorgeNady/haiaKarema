package bee.bee.haiakarema.ui.main.fragments

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import bee.bee.haiakarema.R
import bee.bee.haiakarema.adapters.PdfAdapter
import bee.bee.haiakarema.adapters.VideoAdapter
import bee.bee.haiakarema.base.ActivityFragmentAnnoation
import bee.bee.haiakarema.base.BaseFragment
import bee.bee.haiakarema.databinding.FragmentProjectBinding
import bee.bee.haiakarema.viewmodel.fragments.ProjectsViewModel
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer

@SuppressLint("NonConstantResourceId")
@ActivityFragmentAnnoation(R.layout.fragment_project)
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    override val TAG: String get() = this.javaClass.name
    private val args: ProjectFragmentArgs by navArgs()
    lateinit var id: String

    private val videosList = mutableListOf<String>()
    private val pdfList = mutableListOf<String>()

    private lateinit var viewModel: ProjectsViewModel
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var pdfAdapter: PdfAdapter


    override fun initViewModel() {
        viewModel = ViewModelProvider(this@ProjectFragment).get(ProjectsViewModel::class.java)
        id = args.id
        binding?.apply {
            setupVideoRecyclerView()
            setupPdfRecyclerView()
        }
    }

    override fun setListener() {
        binding?.apply {
            btnBack.btnBack.popBack(this@ProjectFragment)
            getProjectResponse()
        }
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

    private fun FragmentProjectBinding.getProjectResponse() {
        viewModel.getProject(id, progressBar)
            .observe(this@ProjectFragment) { response ->
                tvTitle.text = response.name
                tvIntro.text = response.intro
                notifyVideo(response.video, videosList)
                notifyPdf(response.pdf, pdfList)
            }
    }

    private fun FragmentProjectBinding.setupVideoRecyclerView() {
        videoAdapter = VideoAdapter(this@ProjectFragment)
        rvVideos.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = videoAdapter
        }
    }

    private fun FragmentProjectBinding.setupPdfRecyclerView() {
        pdfAdapter = PdfAdapter(this@ProjectFragment)
        rvPdfs.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = pdfAdapter
        }
    }

    private fun notifyVideo(
        videos: List<String>,
        clashesList: MutableList<String>,
    ) {
        try {
            clashesList.addAll(videos)
            Log.d(TAG, "notify: $videos")
            videoAdapter.apply {
                differ.submitList(clashesList)
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Log.e(TAG, "notify: $e")
        }
    }

    private fun notifyPdf(
        pdf: List<String>,
        clashesList: MutableList<String>,
    ) {
        try {
            pdfList.addAll(pdf)
            Log.d(TAG, "notify: $pdf")
            pdfAdapter.apply {
                differ.submitList(clashesList)
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Log.e(TAG, "notify: $e")
        }
    }
}