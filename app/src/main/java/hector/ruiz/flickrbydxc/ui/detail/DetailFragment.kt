package hector.ruiz.flickrbydxc.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.domain.photo.info.PhotoInfo
import hector.ruiz.flickrbydxc.R
import hector.ruiz.flickrbydxc.databinding.DetailFragmentBinding
import hector.ruiz.flickrbydxc.extensions.loadImage
import hector.ruiz.flickrbydxc.extensions.manageEmptyField

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoDetail: PhotoInfo = args.photoDetails

        binding?.apply {
            this.photoDetailProgress.isVisible = true
            context?.let {
                Picasso.get().loadImage(
                    photoDetail.sizeList,
                    PHOTO_SIZE,
                    this.photoDetailImage,
                    this.photoDetailImageProgress
                )
            }
            this.photoDetailTitle.text =
                view.manageEmptyField(photoDetail.title?.data, R.string.placeholder_title)
            this.photoDetailInfo.text = getString(R.string.author_date).format(
                view.manageEmptyField(photoDetail.owner?.realname, R.string.placeholder_author),
                view.manageEmptyField(photoDetail.dates?.posted, R.string.author_date)
            )
            this.photoDetailDescription.text =
                view.manageEmptyField(
                    photoDetail.description?.data,
                    R.string.placeholder_description
                )
            this.photoDetailProgress.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val PHOTO_SIZE = "Large"
    }
}
