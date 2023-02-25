# Pretty-caldav-politech-schedule
![Build Status](https://img.shields.io/github/actions/workflow/status/michael2to3/pretty-caldav-politech-schedule/gradle.yml?branch=main)
[![Coverage Status](https://img.shields.io/codecov/c/github/michael2to3/pretty-caldav-politech-schedule?style=flat-square)](https://codecov.io/gh/michael2to3/pretty-caldav-politech-schedule)
[![License](https://img.shields.io/github/license/michael2to3/pretty-caldav-politech-schedule?style=flat-square)](https://github.com/michael2to3/pretty-caldav-politech-schedule/blob/main/LICENSE)
[![Docker Image Size](https://img.shields.io/docker/image-size/dockeronfullpc/pretty-caldav-politech-schedule?style=flat-square)](https://hub.docker.com/r/dockeronfullpc/pretty-caldav-politech-schedule)
[![Junit Jupiter](https://img.shields.io/badge/Junit-Jupiter-green?style=flat-square)](https://junit.org/junit5/)
[![Spring Boot](https://img.shields.io/badge/Spring-Boot-green?style=flat-square)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-blue?style=flat-square)](https://gradle.org/)

Pretty-caldav-politech-schedule is a Java project that parses college schedule site APIs and streams WebDAV with pretty descriptions and titles for calendar events. It is released under the GNU 3.0 license.

## Features

- Parses college schedule site APIs
- Streams WebDAV with pretty descriptions and titles for calendar events
- Supports multiple calendar formats
- Easy to use and configure

## Requirements

- Java 17 or above

## Installation

- Download the source code from the GitHub repository.
- Unzip the downloaded file and navigate to the project folder.
- Build the project using the Gradle package command.
- Run the project with the java -jar pretty-webdav-politech-schedule.jar command.

## Usage

- Create a configuration file using the .env. Example as a template.
- Modify the configuration file with your desired settings.
- Run the project with the java -jar pretty-webdav-politech-schedule.jar command.

### Spring Mapping

| HTTP Method | URL                                       | Description                                               |
|-------------|-------------------------------------------|-----------------------------------------------------------|
| GET         | /schedule/                                | Get general information about the service                 |
| GET         | /schedule/groups/id/{group}               | Get a list of `Group` objects by group ID                  |
| GET         | /schedule/group/{faculty}/{group}         | Get a `Group` object by faculty and group name             |
| GET         | /schedule/json/group/id/{group}           | Get a list of `ScheduleOfWeek` objects in JSON format by group ID and date range |
| GET         | /schedule/json/{faculty}/{group}          | Get a list of `ScheduleOfWeek` objects in JSON format by faculty and group name and date range |
| GET         | /schedule/ical/id/{group}                 | Get a iCalendar format of the schedule by group ID and date range |
| GET         | /schedule/ical/{faculty}/{group}          | Get a iCalendar format of the schedule by faculty and group name and date range |
| GET         | /schedule/faculties                       | Get a list of all `Faculty` objects                        |
| GET         | /schedule/ical/{faculty}/{group}/{range}  | Get a iCalendar format of the schedule by faculty and group name and date range |
| GET         | /schedule/json/{faculty}/{group}/{range}  | Get a list of `ScheduleOfWeek` objects in JSON format by faculty and group name and date range |
| GET         | /env/                                     | Get all environment properties                            |
| GET         | /env/source                                     | Get URL for this service                            |
| GET         | /env/target                               | Get URL for scraping site |


## License

This project is released under the GNU 3.0 license. For more information, see the LICENSE file.
