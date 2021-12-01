import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

/**
 * SevensGame
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
 *
 * SevensGame is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * SevensGame is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SevensGame.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */

/**
 * Retrieves the [publishing][org.gradle.api.publish.PublishingExtension] extension.
 */
val org.gradle.api.Project.publishing: org.gradle.api.publish.PublishingExtension
    get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("publishing") as org.gradle.api.publish.PublishingExtension

/**
 * Configures the [publishing][org.gradle.api.publish.PublishingExtension] extension.
 */
fun Project.publishing(configure: org.gradle.api.publish.PublishingExtension.() -> Unit): Unit =
    (this as ExtensionAware).extensions.configure("publishing", configure)


/**
 * Configures the [signing][org.gradle.plugins.signing.SigningExtension] extension.
 */
fun org.gradle.api.Project.signing(configure: org.gradle.plugins.signing.SigningExtension.() -> Unit): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("signing", configure)