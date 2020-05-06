package com.ywt93ycw94.coronavwatcher.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ywt93ycw94.coronavwatcher.R
import com.ywt93ycw94.coronavwatcher.databinding.FragmentGlobalInfoBinding
import com.ywt93ycw94.coronavwatcher.viewmodel.GlobalInfoViewModel

class GlobalInfoFragment : Fragment() {
    private lateinit var globalInfoViewModel: GlobalInfoViewModel
    private lateinit var fragmentGlobalInfoBinding: FragmentGlobalInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        globalInfoViewModel = GlobalInfoViewModel()
        fragmentGlobalInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_global_info, container, false)
        return fragmentGlobalInfoBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("DEBUG: GlobalInfoFragment onActivityCreated()")
        fragmentGlobalInfoBinding.globalViewModel = globalInfoViewModel
        fragmentGlobalInfoBinding.executePendingBindings()
        globalInfoViewModel.onFragmentTriggered()
    }

}