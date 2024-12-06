package com.pet.app.di

import com.pet.app.presentation.screens.petDetail.PetDetailViewModel
import com.pet.app.presentation.screens.petList.PetListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidModules = module {
    viewModel { PetListViewModel(get()) }
    viewModel { PetDetailViewModel(get()) }
}