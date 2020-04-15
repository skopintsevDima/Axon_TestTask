package com.test.axontest.sessions.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.test.axontest.R
import com.test.axontest.sessions.domain.model.UserSession

class UserSessionsAdapter : PagedListAdapter<UserSession, UserSessionViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSessionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val userSessionItemView = layoutInflater.inflate(R.layout.item_user_session, parent, false)
        return UserSessionViewHolder(userSessionItemView)
    }

    override fun onBindViewHolder(holder: UserSessionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserSession>() {
            override fun areItemsTheSame(oldItem: UserSession, newItem: UserSession): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserSession, newItem: UserSession): Boolean =
                oldItem == newItem
        }
    }
}
