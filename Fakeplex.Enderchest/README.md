# [<img src="../.github/icon.png" alt="Fakeplex" width="24" height="24">](../../../) Enderchest

[![Spring Boot][spring-boot]](https://spring.io/projects/spring-boot)

## API Reference

<details id="next-map">
    <summary><b>Get the Next Map</b></summary>

##### URL

```http request
GET /map/${gameType}/next
```

</details>

<details id="upload-map">
    <summary><b>Upload a Map</b></summary>

##### URL

```http request
POST /map/${gameType}/upload?name=${mapName}.zip
Content-Type: application/zip
```

##### Request Data

| Type | Description    |
|------|----------------|
| file | Zip of the map |

</details>

[spring-boot]: https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white