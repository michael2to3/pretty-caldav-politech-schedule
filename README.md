# pretty-caldav-politech-schedule

pretty-caldav-politech-schedule is a Java project that parses college schedule site APIs and streams WebDAV with pretty descriptions and titles for calendar events. It is released under the GNU 3.0 license.

## Features

- Parses college schedule site APIs
- Streams WebDAV with pretty descriptions and titles for calendar events
- Supports multiple calendar formats
- Easy to use and configure

## Requirements

- Java 8 or above

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
