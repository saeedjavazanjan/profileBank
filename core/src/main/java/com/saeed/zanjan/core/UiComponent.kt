package com.saeed.zanjan.core

sealed class UiComponent{

    data class Dialog(
        val title:String,
        val description:String,
        ):UiComponent()
    data class None(
        val message:String,
    ):UiComponent()
}
