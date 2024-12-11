package com.pet.app.di

import com.pet.app.presentation.screens.petDetail.PetDetailViewModel
import com.pet.app.presentation.screens.petList.PetListViewModel
import org.koin.dsl.module

val  desktopModules = module {
    single { PetListViewModel(get()) }
    single { PetDetailViewModel(get()) }
}