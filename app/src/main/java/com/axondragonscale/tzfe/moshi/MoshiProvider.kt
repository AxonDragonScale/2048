package com.axondragonscale.tzfe.moshi

import com.axondragonscale.tzfe.data.GameState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Created by Ronak Harkhani on 17/03/23
 */
object MoshiProvider {
    val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(MatrixAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    val gameStateAdapter by lazy {
        moshi.adapter(GameState::class.java)
    }
}
