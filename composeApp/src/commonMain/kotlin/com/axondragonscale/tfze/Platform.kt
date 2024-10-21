package com.axondragonscale.tfze

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform