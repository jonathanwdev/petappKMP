package com.pet.app.di

import com.pet.app.data.remote.InitializeKtor
import com.pet.app.data.remote.dataSource.RemoteDataSource
import com.pet.app.data.remote.dataSource.RemoteDataSourceImpl
import com.pet.app.domain.repository.Repository
import com.pet.app.domain.repository.RepositoryImpl
import org.koin.dsl.module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf

//val CommonModule: Module = module {
//    single { InitializeKtor() }
//    single { RemoteDataSourceImpl() }
//    single { RepositoryImpl() }
//}

val CommonModule = module {
    singleOf(::InitializeKtor)
    singleOf(::RemoteDataSourceImpl) { bind<RemoteDataSource>() }
    singleOf(::RepositoryImpl) { bind<Repository>() }
}