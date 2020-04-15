package com.test.axontest.sessions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.axontest.R
import com.test.axontest.app.App
import com.test.axontest.sessions.domain.model.UserSession
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
        viewModel.loadSessions().observe(viewLifecycleOwner, Observer { handleSessionsRes(it) })
    }

    private fun handleSessionsRes(sessionsResult: Result<List<UserSession>>) {
        sessionsResult.fold(
            {
                activity?.let { activity ->
                    Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
                }
            },
            { showBottomMsg(rootLayout, R.string.loading_sessions_error) }
        )
    }
}