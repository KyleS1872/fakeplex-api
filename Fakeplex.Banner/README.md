# [<img src="../.github/icon.png" alt="Fakeplex" width="24" height="24">](../../../) Banner

[![Spring Boot][spring-boot]](https://spring.io/projects/spring-boot)

## API Reference

<details id="player-login">
    <summary><b>Player Login</b></summary>

##### URL

```http request
POST /api/server/login/${playerName}
Content-Type: application/json
```

##### Request Data

| Parameter | Type    | Description              |
|-----------|---------|--------------------------|
| name      | string  | Name of the player       |
| realName  | string  | Real Name of the player  |
| uuid      | string  | UUID of the player       |
| accountId | integer | ID of the player         |
| ip        | string  | IP Address of the player |

##### Example

```json
{
  "name": "KyleException",
  "realName": "KyleException",
  "uuid": "aa7e4742-979d-4bbd-a951-dcab1383aba4",
  "accountId": 1,
  "ip": "0.0.0.0"
}
```

</details>

<details id="trigger-ban">
    <summary><b>Trigger Priority Ban</b></summary>

##### URL

```http request
POST /api/banner/trigger/${playerName}
Content-Type: application/json
```

##### Request Data

| Parameter | Type             | Description         |
|-----------|------------------|---------------------|
| target    | [json](#example) | Targets Information |
| cause     | string           | Cause of trigger    |

##### Example

```json
{
  "target": {
    "name": "KyleException",
    "realName": "KyleException",
    "uuid": "aa7e4742-979d-4bbd-a951-dcab1383aba4",
    "accountId": 1,
    "ip": "0.0.0.0"
  },
  "cause": "JOIN_GAME"
}
```

</details>

[spring-boot]: https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white