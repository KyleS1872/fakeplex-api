# [<img src="../.github/icon.png" alt="Fakeplex" width="24" height="24">](../../../) Amplifiers

[![Spring Boot][spring-boot]](https://spring.io/projects/spring-boot)
[![Database][database]](https://github.com/MariaDB/server)
[![Redis][redis]](https://github.com/redis/redis)

## API Reference

<details id="add-amplifier">
    <summary><b>Add an Amplifier to a Group</b></summary>

##### URL

```http request
POST /booster/${boosterGroup}/
Content-Type: application/json
```

##### Request Data

| Parameter  | Type   | Description                    |
|------------|--------|--------------------------------|
| accountId  | int    | ID of the account              |
| playerName | string | Name of the player             |
| uuid       | string | UUID of the player             |
| duration   | int    | Amplifier duration _(seconds)_ |

##### Example

```json
{
  "accountId": 1,
  "playerName": "KyleException",
  "uuid": "aa7e4742-979d-4bbd-a951-dcab1383aba4",
  "duration": 3600
}
```

</details>

<details id="get-all-amplifiers">
    <summary><b>Get all Active and Future Amplifiers</b></summary>

##### URL

```http request
GET /booster/
```

</details>

<details id="get-all-amplifiers-for-group">
    <summary><b>Get all Active and Future Amplifiers for a Group</b></summary>

##### URL

```http request
GET /booster/${boosterGroup}/
```

</details>

[spring-boot]: https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white

[database]: https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white

[redis]: https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white