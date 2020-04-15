package com.test.axontest.sessions.presentation.list

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.axontest.R
import com.test.axontest.sessions.domain.model.UserSession
import java.text.SimpleDateFormat
import java.util.*

class UserSessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val sessionDateView = itemView.findViewById<AppCompatTextView>(R.id.sessionDateView)
    private val sessionPhotoView = itemView.findViewById<AppCompatImageView>(R.id.sessionPhotoView)

    fun bind(userSession: UserSession) {
        sessionDateView.text = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ).format(userSession.timestamp)

        val resources = itemView.context.resources
        val sessionPhotoViewWidthPx = resources.getDimensionPixelSize(R.dimen.sessionPhotoWidth)
        val sessionPhotoViewHeightPx = resources.getDimensionPixelSize(R.dimen.sessionPhotoHeight)
        Picasso.get()
            .load(userSession.photoUri)
            .placeholder(R.drawable.ic_image_grey_24dp)
            .error(R.drawable.ic_broken_image_grey_24dp)
            .resize(sessionPhotoViewWidthPx, sessionPhotoViewHeightPx)
            .centerCrop()
            .into(sessionPhotoView)
    }
}