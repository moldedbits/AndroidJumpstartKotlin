package com.moldedbits.android

import com.moldedbits.android.api.MockApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MockApiModule::class))
internal interface MockApiComponent : ApiComponent
