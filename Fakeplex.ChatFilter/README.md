# [<img src="../.github/icon.png" alt="Fakeplex" width="24" height="24">](../../../) Chat Filter

[![Spring Boot][spring-boot]](https://spring.io/projects/spring-boot)
[![Google Sheets][google-sheets]](https://docs.google.com/spreadsheets/d/1KPF4i9pHsMDscoLLIQ6Jchd-V07fQ_TTLCn188FPk1c/export?format=csv)

## API Reference

<details open>
    <summary><b>Chat Filter</b></summary>

##### URL

```http request
POST /content/item/moderate
Content-Type: application/json
Authentication: oUywMpwZcIzZO5AWnfDx
```

##### Request Data

| Parameter | Type | Description          |
|-----------|------|----------------------|
| content   | json | Message(s) to filter |

##### Example

```json
{
  "content": {
    "createInstant": 1672531200000,
    "senderDisplayName": "KyleException",
    "senderId": "aa7e4742-979d-4bbd-a951-dcab1383aba4",
    "parts": [
      {
        "type": "text",
        "content": "No profanity here!"
      }
    ],
    "applicationId": "34018d65-466d-4a91-8e92-29ca49f022c4"
  }
}
```

</details>

[spring-boot]: https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white

[google-sheets]: https://img.shields.io/badge/Google_Sheets-34A853?style=for-the-badge&logo=googlesheets&logoColor=fff