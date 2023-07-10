package com.assignment.fitpeo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.fitpeo.R
import com.assignment.fitpeo.main.DetailActivity
import com.assignment.fitpeo.main.FitpeoApplication
import com.assignment.fitpeo.model.ApiResponseData
import com.assignment.fitpeo.model.ApiResponseDataItem
import com.assignment.fitpeo.utility.Constants
import com.squareup.picasso.Picasso

class ImageListAdapter(var mContext: Context, imageData: ApiResponseData):
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    var context: Context
    var imageData: ApiResponseData
    init {
        this.context = mContext
        this.imageData = imageData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_photos, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(
            position, mContext, imageData
        )
    }

    override fun getItemCount(): Int {
        return imageData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            position: Int,
            mContext: Context,
            imageData: ApiResponseData,

            ) {
            val cvMainContainer = itemView.findViewById(R.id.cvMainContainer) as CardView
            val ivThumnail = itemView.findViewById(R.id.iv_thumnail) as AppCompatImageView
            val tvAlbumID = itemView.findViewById(R.id.tv_albumID) as AppCompatTextView
            val tvID = itemView.findViewById(R.id.tv_id) as AppCompatTextView
            val tvDescription = itemView.findViewById(R.id.tv_description) as AppCompatTextView
            tvAlbumID.text = imageData.get(position).albumId.toString()
            tvID.text = imageData.get(position).id.toString()
            tvDescription.text = imageData.get(position).title
            Picasso.get().load(imageData.get(position).url).into(ivThumnail);
            cvMainContainer.setOnClickListener {
                startDetailActivity(imageData.get(position), mContext)
            }

        }

        private fun startDetailActivity(imageData: ApiResponseDataItem, mContext: Context) {
            val gsonString = FitpeoApplication.getGsonInstance().toJson(imageData)
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_PARAM_IMAGE_DETAIL, gsonString)
            mContext.startActivity(intent)
        }
    }
}