/*
 * Komunumo – Open Source Community Manager
 * Copyright (C) 2017 Java User Group Switzerland
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ch.komunumo.server.authorization

import io.jsonwebtoken.Jwts
import mu.KotlinLogging
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.request.header
import org.jetbrains.ktor.util.AttributeKey

class Authorization {

    companion object {

        private val signingKey = "This is a test!"
        private val UserAttribute = AttributeKey<String>("user")
        private val logger = KotlinLogging.logger {}

        fun checkAuthorization(call: ApplicationCall) {
            val authorization = call.request.header("Authorization")
            if (authorization != null && authorization.toLowerCase().startsWith("bearer")) {
                val token = authorization.split(" ")[1]
                try {
                    val claims = Jwts.parser()
                            .setSigningKey(signingKey)
                            .parseClaimsJws(token);
                    val email = claims.body["email"]
                    if (email != null) {
                        call.attributes.put(UserAttribute, email.toString())
                        logger.info { "User with email '$email' successfully authorized." }
                        // TODO get user object and add it to the call attributes (instead of the email address)
                    }
                } catch (e: Exception) {
                    logger.warn(e) { "Can't authorize the user with token '$token'!" }
                }
            }
        }

    }

}
