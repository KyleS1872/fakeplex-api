# [<img src="../.github/icon.png" alt="Fakeplex" width="24" height="24">](../../../) Accounts

[![Spring Boot][spring-boot]](https://spring.io/projects/spring-boot)
[![Database][database]](https://github.com/MariaDB/server)

## API Reference

<!--
- [Player Account](#player-account)
- [Dominate](#dominate)
- [Pets](#pets)
-->

### Player Account

<details id="player-login">
    <summary><b>Player Login</b></summary>

##### URL

```http request
POST /PlayerAccount/Login
Content-Type: application/json
```

##### Request Data

| Parameter | Type   | Description              |
|-----------|--------|--------------------------|
| Name      | string | Name of the player       |
| IpAddress | string | IP Address of the player |
| Uuid      | string | UUID of the player       |

##### Example

```json
{
  "Name": "KyleException",
  "IpAddress": "0.0.0.0",
  "Uuid": "aa7e4742-979d-4bbd-a951-dcab1383aba4"
}
```

</details>

<details id="apply-kits-to-account">
    <summary><b>Apply Kits to account</b></summary>

##### URL

```http request
POST /PlayerAccount/ApplyKits
Content-Type: application/json
```

##### Request Data

| Type   | Description        |
|--------|--------------------|
| string | Name of the player |

##### Example

```json
"KyleException"
```

</details>

<details id="get-an-account-by-uuid">
    <summary><b>Get an Account by UUID</b></summary>

##### URL

```http request
POST /PlayerAccount/GetAccountByUUID
Content-Type: application/json
```

##### Request Data

| Type   | Description        |
|--------|--------------------|
| string | UUID of the player |

##### Example

```json
"aa7e4742-979d-4bbd-a951-dcab1383aba4"
```

</details>

<details id="get-an-account-by-name">
    <summary><b>Get an Account by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/GetAccount
Content-Type: application/json
```

##### Request Data

| Type   | Description        |
|--------|--------------------|
| string | Name of the player |

##### Example

```json
"KyleException"
```

</details>

<details id="get-account-matches-by-name">
    <summary><b>Get Account matches by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/GetMatches
Content-Type: application/json
```

##### Request Data

| Type   | Description    |
|--------|----------------|
| string | Name to search |

##### Example

```json
"ky"
```

</details>

<details id="get-a-punishment-client-by-name">
    <summary><b>Get a Punishment Client by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/GetPunishClient
Content-Type: application/json
```

##### Request Data

| Type   | Description        |
|--------|--------------------|
| string | Name of the player |

##### Example

```json
"KyleException"
```

</details>

<details id="punish-an-account-by-name">
    <summary><b>Punish an Account by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/Punish
Content-Type: application/json
```

##### Request Data

| Parameter | Type    | Description                |
|-----------|---------|----------------------------|
| Target    | string  | Name of the player         |
| Category  | string  | Punishment Category        |
| Reason    | string  | Reason for punishment      |
| Admin     | string  | Name of the staff member   |
| Duration  | integer | Duration of the punishment |
| Sentence  | string  | Punishment Type            |
| Severity  | integer | Punishment Severity        |

##### Example

```json
{
  "Target": "KyleException",
  "Category": "Other",
  "Reason": "Testing Via API",
  "Admin": "KyleException",
  "Duration": -1,
  "Sentence": "Ban",
  "Severity": 1
}
```

</details>

<details id="remove-a-punishment-by-punishment-id">
    <summary><b>Remove a Punishment by Punishment ID</b></summary>

##### URL

```http request
POST /PlayerAccount/RemovePunishment
Content-Type: application/json
```

##### Request Data

| Parameter    | Type    | Description                   |
|--------------|---------|-------------------------------|
| PunishmentId | integer | ID of the punishment          |
| Target       | string  | Name of the player            |
| Reason       | string  | Reason for punishment removal |
| Admin        | string  | Name of the staff member      |

##### Example

```json
{
  "PunishmentId": 1,
  "Target": "KyleException",
  "Reason": "Testing Via API",
  "Admin": "KyleException"
}
```

</details>

<details id="reward-an-account-gems-by-name">
    <summary><b>Reward an Account Gems by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/GemReward
Content-Type: application/json
```

##### Request Data

| Parameter | Type    | Description        |
|-----------|---------|--------------------|
| Source    | string  | Source of reward   |
| Name      | string  | Name of the player |
| Amount    | integer | Amount to reward   |

##### Example

```json
{
  "Source": "Accounts API",
  "Name": "KyleException",
  "Amount": 1000
}
```

</details>

<details id="reward-an-account-coins-by-name">
    <summary><b>Reward an Account Coins by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/CoinReward
Content-Type: application/json
```

##### Request Data

| Parameter | Type    | Description        |
|-----------|---------|--------------------|
| Source    | string  | Source of reward   |
| Name      | string  | Name of the player |
| Amount    | integer | Amount to reward   |

##### Example

```json
{
  "Source": "Accounts API",
  "Name": "KyleException",
  "Amount": 1000
}
```

</details>

<details id="add-a-known-sales-package-to-an-account-by-name">
    <summary><b>Add a Known Sales Package to an Account by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/PurchaseKnownSalesPackage
Content-Type: application/json
```

##### Request Data

| Parameter      | Type    | Description                |
|----------------|---------|----------------------------|
| AccountName    | string  | Name of the player         |
| UsingCredits   | boolean | Is purchased using credits |
| SalesPackageId | integer | ID of the sales package    |

##### Example

```json
{
  "AccountName": "KyleException",
  "UsingCredits": false,
  "SalesPackageId": 1
}
```

</details>

<details id="add-an-unknown-sales-package-to-an-account-by-name">
    <summary><b>Add an Unknown Sales Package to an Account by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/PurchaseUnknownSalesPackage
Content-Type: application/json
```

##### Request Data

| Parameter        | Type    | Description                |
|------------------|---------|----------------------------|
| AccountName      | string  | Name of the player         |
| SalesPackageName | string  | Name of the sales package  |
| CoinPurchase     | boolean | Is purchased using coins   |
| Cost             | integer | Cost of the sales package  |
| Premium          | boolean | Is a premium sales package |

##### Example

```json
{
  "AccountName": "KyleException",
  "SalesPackageName": "Easter Bunny Morph",
  "CoinPurchase": true,
  "Cost": 0,
  "Premium": false
}
```

</details>

<details id="save-a-custom-build-to-an-account-by-name">
    <summary><b>Save a Custom Build to an Account by Name</b></summary>

##### URL

```http request
POST /PlayerAccount/SaveCustomBuild
Content-Type: application/json
```

##### Request Data

| Parameter               | Type    | Description                |
|-------------------------|---------|----------------------------|
| PlayerName              | string  | Name of the player         |
| Name                    | string  | Name of the build          |
| PvpClass                | string  | Type of class              |
| SwordSkill              | string  | Sword Skill                |
| SwordSkillLevel         | integer | Sword Skill Level          |
| AxeSkill                | string  | Axe Skill                  |
| AxeSkillLevel           | integer | Axe Skill Level            |
| BowSkill                | string  | Bow Skill                  |
| BowSkillLevel           | integer | Bow Skill Level            |
| ClassPassiveASkill      | string  | Passive A Skill            |
| ClassPassiveASkillLevel | integer | Passive A Skill Level      |
| ClassPassiveBSkill      | string  | Passive B Skill            |
| ClassPassiveBSkillLevel | integer | Passive B Skill Level      |
| GlobalPassiveSkill      | string  | Global Passive Skill       |
| GlobalPassiveSkillLevel | integer | Global Passive Skill Level |
| Slots                   | json[]  | Items within build         |
| ItemTokens              | integer | Amount of item tokens      |

##### Example

```json
{
  "PlayerName": "KyleException",
  "Name": "Default Build",
  "PvpClass": "Global",
  "SwordSkill": "Disengage",
  "SwordSkillLevel": 3,
  "AxeSkill": "Agility",
  "AxeSkillLevel": 2,
  "BowSkill": "Napalm Shot",
  "BowSkillLevel": 3,
  "ClassPassiveASkill": "Barrage",
  "ClassPassiveASkillLevel": 2,
  "ClassPassiveBSkill": "Barbed Arrows",
  "ClassPassiveBSkillLevel": 1,
  "GlobalPassiveSkill": "Resistance",
  "GlobalPassiveSkillLevel": 1,
  "Slots": [
    {},
    {},
    {
      "Name": "Standard Bow",
      "Material": "BOW",
      "Amount": 1
    },
    {
      "Name": "Ranger Arrows",
      "Material": "ARROW",
      "Amount": 24
    },
    {},
    {},
    {},
    {},
    {}
  ],
  "ItemTokens": 1
}
```

</details>

### Dominate

<details id="get-skills-using-skill-name">
    <summary><b>Get Skills using Skill Name</b></summary>

##### URL

```http request
POST /Dominate/GetSkills
Content-Type: application/json
```

##### Request Data

| Type   | Description                          |
|--------|--------------------------------------|
| json[] | Skills to add/retrieve from database |

##### Example

```json
[
  {
    "Name": "Swordsmanship",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Immolate",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Ice Prison",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Bulls Charge",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Riposte",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Napalm Shot",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Sharpshooter",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Wolfs Fury",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Illusion",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Null Blade",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Shield Smash",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Roped Axe Throw",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Static Lazer",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Bloodlust",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Wolfs Pounce",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Level Field",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Mana Pool",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Agility",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Recall",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Backstab",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Flesh Hook",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Void",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Barrage",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Brute Class",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Cleave",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Rupture",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Leap",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Mana Regeneration",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Stampede",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Vengeance",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Magma Blade",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Fissure",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Hilt Smash",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Resistance",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Life Bonds",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Evade",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Smoke Arrow",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Silencing Arrow",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Mage Class",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Viper Strikes",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Takedown",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Assassin Class",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Fire Blast",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Arctic Armor",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Overcharge",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Disengage",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Vitality Spores",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Deflection",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Overwhelm",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Inferno",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Break Fall",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Defensive Stance",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Explosive Arrow",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Longshot",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Combo Attack",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Whirlwind Axe",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Flash",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Intimidation",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Seismic Slam",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Knight Class",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Blink",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Pin Down",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Hold Position",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Colossus",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Ranger Class",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Glacial Blade",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Fortitude",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Roped Arrow",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Heavy Arrows",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Marked for Death",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Barbed Arrows",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Block Toss",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Lightning Orb",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Healing Shot",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Dwarf Toss",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Shocking Strikes",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Crippling Blow",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Smoke Bomb",
    "Level": 1,
    "SalesPackage": {
    }
  },
  {
    "Name": "Blizzard",
    "Level": 1,
    "SalesPackage": {
    }
  }
]
```

</details>

### Pets

<details id="add-a-pet-to-an-account-by-id">
    <summary><b>Add a Pet to an Account by ID</b></summary>

##### URL

```http request
POST /Pets/AddPet
Content-Type: application/json
```

##### Request Data

| Parameter | Type    | Description      |
|-----------|---------|------------------|
| AccountId | integer | ID of the player |
| PetName   | string  | Name of the pet  |
| PetType   | string  | Type of pet      |

##### Example

```json
{
  "AccountId": 1,
  "PetName": "Test",
  "PetType": "PIG"
}
```

</details>

<details id="update-a-pet-on-an-account-by-id-and-pet-type">
    <summary><b>Update a Pet on an Account by ID and Pet Type</b></summary>

##### URL

```http request
POST /Pets/UpdatePet
Content-Type: application/json
```

##### Request Data

| Parameter | Type    | Description      |
|-----------|---------|------------------|
| AccountId | integer | ID of the player |
| PetName   | string  | Name of the pet  |
| PetType   | string  | Type of pet      |

##### Example

```json
{
  "AccountId": 1,
  "PetName": "Test",
  "PetType": "PIG"
}
```

</details>

[spring-boot]: https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white

[database]: https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white