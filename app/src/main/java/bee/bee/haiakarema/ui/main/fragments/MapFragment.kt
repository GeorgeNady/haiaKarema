package bee.bee.haiakarema.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        }
    }
}