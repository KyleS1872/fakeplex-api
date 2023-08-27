# [<img src="../.github/icon.png" alt="Fakeplex" width="24" height="24">](../../../) Anti-Spam

[![Spring Boot][spring-boot]](https://spring.io/projects/spring-boot)
[![Database][database]](https://github.com/MariaDB/server)

## API Reference

<details open>
    <summary><b>Check a message for spam</b></summary>

##### URL

```http request
POST /chat/${plugin}/
Content-Type: application/json
```

##### Request Data

| Parameter  | Type    | Description            |
|------------|---------|------------------------|
| playerName | string  | Name of the player     |
| uuid       | string  | UUID of the player     |
| region     | string  | Server region          |
| server     | string  | Origin of the message  |
| message    | string  | Message to check       |
| time       | integer | Time of message _(ms)_ |

##### Example

```json
{
  "playerName": "KyleException",
  "uuid": "aa7e4742-979d-4bbd-a951-dcab1383aba4",
  "region": "Unknown",
  "server": "Unknown",
  "message": "Hello There",
  "time": 1672531200000
}
```

</details>

[spring-boot]: https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white

[database]: https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white