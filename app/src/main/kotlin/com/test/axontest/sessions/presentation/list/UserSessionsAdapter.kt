package com.test.axontest.sessions.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.axontest.R
import com.test.axontest.sessions.domain.model.UserSession

class UserSessionsAdapter(private var userSessions: List<UserSession>): RecyclerView.Adapter<UserSessionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSessionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val userSessionItemView = layoutInflater.inflate(R.layout.item_user_session, parent, false)
        return UserSessionViewHolder(userSessionItemView)
    }

    override fun onBindViewHolder(holder: UserSessionViewHolder, position: Int) {
        holder.bind(userSessions[position])
    }

    override fun getItemCount(): Int = userSessions.size

    fun setItems(userSession: List<UserSession>) {
        this.userSessions = userSession
        notifyDataSetChanged()
    }
}
