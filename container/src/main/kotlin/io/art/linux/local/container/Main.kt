package io.art.linux.local.container

import io.art.configurator.kotlin.configurator
import io.art.launcher.kotlin.activator
import io.art.logging.kotlin.logger
import io.art.logging.kotlin.logging
import io.art.scheduler.kotlin.scheduler
import io.art.transport.kotlin.transport

fun main() = activator {
    logging()
    transport()
    scheduler()
    configurator()
    onLaunch {
        logger { info("Hello, ART!") }
    }
    launch()
}