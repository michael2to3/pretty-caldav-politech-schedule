# Pretty-caldav-politech-schedule
[![Build Status](/github/actions/workflow/status/michael2to3/pretty-caldav-politech-schedule/build+)](https://travis-ci.com/michael2to3/pretty-caldav-politech-schedule)
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

| HTTP Method | URL | Source | Description |
| --- | --- | --- | --- |
| GET | /source/ | application.source | Get variable from application.source |
| GET | /sch/{groupId} | HandlerSchedule.generateScheduleJson | Get schedule in JSON format for the specified group ID, start date, and end date |
| GET | /sch/{groupId}/ics | HandlerSchedule.generateScheduleIcal | Get schedule in iCalendar format for the specified group ID, start date, and end date |
| GET | /facultics/ | HandlerSchedule.generateFacultiesJson | Get faculties in JSON format |
| GET | /groups/{id} | HandlerSchedule.generateGroupsJson | Get groups in JSON format for the specified group ID |
| GET | /group/{nameOfFacult}/{nameOfGroup} | HandlerSchedule.generateGroupJson | Get group in JSON format for the specified faculty name and group name |


## License

This project is released under the GNU 3.0 license. For more information, see the LICENSE file.
