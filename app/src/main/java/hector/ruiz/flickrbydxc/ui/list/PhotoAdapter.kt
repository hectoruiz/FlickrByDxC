package hector.ruiz.flickrbydxc.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hector.ruiz.domain.photo.info.PhotoInfo
import hector.ruiz.flickrbydxc.R
import hector.ruiz.flickrbydxc.databinding.PhotoItemBinding
import hector.ruiz.flickrbydxc.extensions.loadImage
import hector.ruiz.flickrbydxc.extensions.manageEmptyField
import javax.inject.Inject

class PhotoAdapter @Inject constructor() :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    var onDetailClick: ((PhotoInfo?) -> Unit)? = null
    private var photos: List<PhotoInfo?> = emptyList()

    fun setList(photos: List<PhotoInfo?>) {
        this.photos = photos
    }

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PhotoItemBinding.bind(view)

        init {
            binding.photo.setOnClickListener {
                onDetailClick?.invoke(photos[adapterPosition])
            }
        }

        fun bind(photoInfo: PhotoInfo?) {
            with(binding) {
                photoProgress.isVisible = true
                Picasso.get().loadImage(
                    photoInfo?.sizeList,
                    PHOTO_SIZE,
                    this.photoImage,
                    this.photoProgress
                )
                this.photoTitle.text =
                    itemView.manageEmptyField(photoInfo?.title?.data, R.string.placeholder_title)
                this.photoAuthor.text =
                    itemView.manageEmptyField(
                        photoInfo?.owner?.realname,
                        R.string.placeholder_author
                    )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(
            layoutInflater.inflate(
                R.layout.photo_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoInfo = photos[position]
        holder.bind(photoInfo)
    }

    override fun getItemCount(): Int = photos.size

    private companion object {
        private const val PHOTO_SIZE = "Thumbnail"
    }
}
