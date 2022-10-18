package com.saeed.zanjan.core


sealed class ProgressBarState{
    object Loading:ProgressBarState()
    object Idle:ProgressBarState()


}