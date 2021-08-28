package bee.bee.haiakarema.ui.main.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.util.Log
import android.view.View.SCROLLBARS_OUTSIDE_OVERLAY
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.getSystemService
import bee.bee.haiakarema.R
import bee.bee.haiakarema.base.ActivityFragmentAnnoation
import bee.bee.haiakarema.base.BaseFragment
import bee.bee.haiakarema.databinding.FragmentMapBinding


@SuppressLint("NonConstantResourceId")
@ActivityFragmentAnnoation(R.layout.fragment_map)
class MapFragment : BaseFragment<FragmentMapBinding>() {

    override val TAG: String
        get() = this.javaClass.name

    override fun initViewModel() {

    }

    override fun setListener() {
        binding?.apply {
            webViewHandler()
        }
    }

    private fun FragmentMapBinding.webViewHandler() {
        val url = "http://www.egyptianentrepreneurshub.com/start/?select=&lp_s_loc=&lp_s_tag=&lp_s_cat=&s=home&post_type=listing"
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }

    /*private fun getScaless(): Int {
        val windowService : WindowManager = requireActivity().getSystemService(WINDOW_SERVICE) as WindowManager
        val display = (windowService).defaultDisplay
        val width = display.width
        val value =
        Log.d(TAG, "getScale: $width")
        return width
    }*/
}