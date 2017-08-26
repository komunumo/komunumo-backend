*Komunumo Backend*
==================

[![Build Status](https://travis-ci.org/komunumo/komunumo-backend.svg?branch=master)](https://travis-ci.org/komunumo/komunumo-backend) [![Stories in Ready](https://badge.waffle.io/komunumo/komunumo-backend.png?label=ready&title=ready)](http://waffle.io/komunumo/komunumo-backend) [![gitmoji](https://img.shields.io/badge/gitmoji-%20😜%20😍-FFDD67.svg)](https://gitmoji.carloscuesta.me)

**Open Source Community Manager**

*Copyright (C) 2017 Java User Group Switzerland*

This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.

## The Name

*Komunumo* is an [esperanto](https://wikipedia.org/wiki/Esperanto) noun with a meaning of *community*.

## Installation

### From Source

#### Prerequisites

- [git](https://git-scm.com)
- [Java](https://www.oracle.com/technetwork/java/javase/downloads) 8 or newer

#### Build

1. Clone this repository to your computer: `git clone https://github.com/komunumo/komunumo-backend.git`
2. Enter the newly created project directory: `cd komunumo-backend`
3. Build the artifact from source: `./gradlew assemble`
4. The artifact can be found in the following directory: `build/libs`

#### Run

1. Place your [configuration file](#configuration) in the directory: `~/.komunumo`
2. Start the artifact: `./gradlew run`
3. To stop the running server, press: `CTRL+C`

### Using Docker

#### Prerequisites

- [Docker](https://www.docker.com)

#### Command Line

`docker run -it -p [localport]:8080 -v [datadir]:/root/.komunumo --name komunumo --rm komunumo/komunumo-backend`

Replace `[localport]` with the port number you would like to assign to your running *Komunumo*  backend server and replace `[datadir]` with the directory on your local drive that contains the *Komunumo*  backend [configuration file](#configuration). This directory will be used to store the business data, too.

We suggest to start the *Komunumo* backend server using the `-it` and `--rm` command line parameters to activate the interactive mode (you will see all logging output directkly at the console) and to remove the container after the server was shut down. It is always a good idea to assign a descriptive name to the container (`--name`).

#### Example

`docker run -it -p 8080:8080 -v ~/.komunumo:/root/.komunumo --name komunumo --rm komunumo/komunumo-backend`

This command uses the local port `8080` for the *Komunumo* backend server. The [configuration file](#configuration) is located in the folder `~/.komunumo` which will be used to store the business data, too. The server will be started in interactive mode (`-it`), so all logging output will be shown directly on the console and the server can stopped using `Ctrl+C` at any time. The running container will have the name `komunumo` so it easy to identify if you run a lot of docker containers (`--name`). The container will be automatically removed after the *Komunumo* backend server was shut down (`--rm`).

## Configuration

In your home directory create a new directory with the name `.komunumo` (yes, the name of the directory starts with a dot, which hides the directory on unix systems by default). Inside of this directory create a text file with the name `komunumo.cfg` which you can use to configure *Komunumo*. This configuration file can contain as many empty lines and comments (lines starting with a `#` character) as you like.

### Example configuration file
```
# Security
token.signing.key = very secret text 
token.expiration.time = 480

# Administrator
admin.firstname = John
admin.lastname = Doe
admin.email = john.doe@mydomain.com

# SMTP Server information
smtp.server = mail.mydomain.com
smtp.port = 465
smtp.user = no-reply@mydomain.com
smtp.password = very secret password
smtp.useSSL = true
smtp.from = no-reply@mydomain.com

# Server
server.baseURL = https://mydomain.com
```

Most configuration options are self-descriptive. The `token.signing.key` is just text like a password which is used to sign the JSON Web Token with a private key. The `token.expiration.time` is a number specifying the time a JSON Web Token is valid until it expires (in minutes, 480 minutes are 8 hours = about 8 hours after a successful authorization the user has to authorize again). The `server.baseURL` is used as a prefix for automatically generated links like in the `Location` header of a response and in emails.

## Throughput

[![Throughput Graph](https://graphs.waffle.io/komunumo/komunumo-backend/throughput.svg)](https://waffle.io/komunumo/komunumo-backend/metrics/throughput)

