package com.example.demoapp.ui

sealed class UIState {
    object LoadingState : UIState()
    object DataState : UIState()
    object ErrorState : UIState()
}