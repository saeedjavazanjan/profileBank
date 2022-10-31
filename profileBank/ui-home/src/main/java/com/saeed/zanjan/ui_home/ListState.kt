package com.saeed.zanjan.ui_home

import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.ProgressBarState
import com.saeed.zanjan.core.UiComponent
import com.saeed.zanjan.domain.FirstBanners

data class ListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val firstBanners:List<FirstBanners> = listOf(),
    val response:String="",

    )
