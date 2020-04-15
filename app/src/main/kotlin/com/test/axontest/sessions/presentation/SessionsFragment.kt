package com.test.axontest.sessions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.axontest.R
import com.test.axontest.app.App
import com.test.axontest.sessions.domain.model.UserSession
import com.test.axontest.sessions.presentation.list.UserSessionsAdapter
import com.test.axontest.util.showBottomMsg
import kotlinx.android.synthetic.main.fragment_sessions.*
import javax.inject.Inject

class SessionsFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SessionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sessions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDependencies()
        initUI()
    }

    private fun initDependencies() {
        App.appComponent.fragmentComponent().create().inject(this)
        viewModel = viewModelFactory.create(SessionsViewModel::class.java)
    }

    private fun initUI() {
        activity?.let { activity ->
            val llm = LinearLayoutManager(activity)
            llm.orientation = RecyclerView.VERTICAL
            sessionsView.layoutManager = llm
            sessionsView.addItemDecoration(DividerItemDecoration(activity, llm.orientation))
            viewModel.loadSessions().observe(viewLifecycleOwner, Observer { handleSessionsRes(it) })
        }
    }

    private fun handleSessionsRes(sessionsResult: Result<PagedList<UserSession>>) {
        sessionsResult.fold(
            {
                val adapter = UserSessionsAdapter()
                adapter.submitList(it)
                sessionsView.adapter = adapter
            },
            { showBottomMsg(rootLayout, R.string.loading_sessions_error) }
        )
    }
}