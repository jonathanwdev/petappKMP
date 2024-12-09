package com.pet.app.di

import com.pet.app.presentation.screens.petList.PetListViewModel
import org.koin.dsl.module

val  desktopModules = module {
    single { PetListViewModel(get()) }
}