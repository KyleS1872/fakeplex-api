[![Logo](.github/logo.png)](../../)

<!--
[![License: MIT][license]](LICENSE)
[![Java][java]](https://openjdk.java.net/projects/jdk/11/)
[![Discord][discord]](http://discord.fakeplex.xyz)
[![Releases][release]](../../releases)
-->

<div align="center">
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge" alt="License: MIT"></a>
  <a href="https://openjdk.java.net/projects/jdk/11/"><img src="https://img.shields.io/badge/Java-11-%23ED8B00.svg?style=for-the-badge&logo=openjdk" alt="Java"></a>
  <a href="http://discord.fakeplex.xyz"><img src="https://img.shields.io/discord/999218379003011092.svg?style=for-the-badge&color=7289DA&label=discord&logoColor=ffffff&logo=discord" alt="Discord"></a>
  <a href="../../releases"><img src="https://img.shields.io/badge/Version-Latest-green.svg?style=for-the-badge" alt="Releases"></a>
</div>

---

This project comprises a suite of Spring Boot applications, each functioning as individual microservices. These
microservices are dedicated to tasks such as managing player logins, game boosters, spam detection, chat filtering, and
facilitating world uploading and downloading. Additionally, the project includes specialized libraries designed to
support these microservices.

## Features

### APIs

[![Spring Boot][spring-boot]](https://spring.io/projects/spring-boot)
[![Database][database]](https://github.com/MariaDB/server)
[![Redis][redis]](https://github.com/redis/redis)

- [Accounts](Fakeplex.Accounts/README.md)
- [Amplifiers](Fakeplex.Amplifiers/README.md)
- [Anti-Spam](Fakeplex.AntiSpam/README.md)
- [Banner](Fakeplex.Banner/README.md)
- [Chat Filter](Fakeplex.ChatFilter/README.md)
- [Enderchest](Fakeplex.Enderchest/README.md)

<!--
### Other

- [Discord Bot](Fakeplex.Discord/README.md)
- [Reports Site](Fakeplex.ReportSite/README.md)
- [Management Site](Fakeplex.Manager/README.md)
-->

## Usage

### API Testing

The APIs provided in this project have been meticulously crafted and thoroughly tested using Insomnia, a powerful API
testing and development tool. This ensures that the endpoints function reliably and efficiently.

[![Insomnia][insomnia]](https://insomnia.rest/)

For your convenience, I have exported an Insomnia template that contains all the API endpoints. You can import this
template into your Insomnia instance by downloading it from [this link][insomnia-link]. This will enable you to
quickly set up and explore the API interactions within Insomnia.

## Code Formatting

This project follows the code formatting guidelines provided by [Google Java
Format](https://github.com/google/google-java-format). Please ensure that you have Google
Java Format installed and configured in your development environment. The codebase is formatted automatically using
Google Java Format before every commit.

## Support

If you have any questions, need help, or want to discuss anything related to this project, feel free to join our Discord
server:

[![Discord Server][discord]](http://discord.fakeplex.xyz)

We're here to help and provide assistance. Don't hesitate to ask questions or engage in discussions!

## License

This project is licensed under the terms of the **MIT** license.
> You can see the full license [here](LICENSE).

[spring-boot]: https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white

[discord]: https://img.shields.io/discord/999218379003011092.svg?color=7289DA&label=Join%20our%20Discord&logo=discord&logoColor=ffffff&style=for-the-badge

[redis]: https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white

[database]: https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white

[insomnia]: https://img.shields.io/badge/Insomnia-4000BF?style=for-the-badge&logo=insomnia&logoColor=fff

[insomnia-link]: http://discord.fakeplex.xyz