# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [1.2.0](https://github.com/prezdev88/pacman-api/compare/v1.2.0%0Dv1.1.0) (2024-12-14)


### Features

* add all endpoints in README.md file ([30981c9](https://github.com/prezdev88/pacman-api/commit/30981c99f6dd4fc01a183f9177b93f9147413655))
* **endpoint:** add GET packages history by package name ([8ed8dce](https://github.com/prezdev88/pacman-api/commit/8ed8dceb792a625e9d269072c891273df1460166))


### Bug Fixes

* add spaces in get installed log command to search from complete package name in notes file ([b202791](https://github.com/prezdev88/pacman-api/commit/b2027910add26b4c6ce066387bcbffe59fdbfaa9))


### Refactor

* remove password of endpoints ([a0f7e00](https://github.com/prezdev88/pacman-api/commit/a0f7e00b2da40e63ea51eb48e5543992c600f7cb))

## 1.1.0 (2024-12-10)


### Features

* add basic html gui to api. fix readme file (http code in Response when root password is wrong).  add command log in LinuxCommandRunnerImpl. add groups commands in notes file. ([34ac264](https://github.com/prezdev88/pacman-api/commit/34ac264b0243b0b189d9c6c628ce174f1864f5aa))
* add CommandRunner service and LinuxCommandRunnerImpl ([9895bcf](https://github.com/prezdev88/pacman-api/commit/9895bcff2ce45f03defd2124cc7ad25f30a61935))
* add compile and run with graalvm jdk 21. add support to graalvm ([f095a62](https://github.com/prezdev88/pacman-api/commit/f095a62e4ff15d363ca48302599d21e91f2b801f))
* add foreign service and controller. update README.md file ([f480842](https://github.com/prezdev88/pacman-api/commit/f480842c927ad569712dace7237839bb04cb02d1))
* add four endpoints and README.md file ([64ef017](https://github.com/prezdev88/pacman-api/commit/64ef0174afc2e38faf38472bead79c0e0c9037f1))
* add get Lite Packages. add pacman -Sy before get upgrade package. add get package by name. ([20606c2](https://github.com/prezdev88/pacman-api/commit/20606c2a4b2b2ab854d9f908cb15df591a83cb52))
* add get upgrade packages feature ([40aa44e](https://github.com/prezdev88/pacman-api/commit/40aa44edfd5e37adb2e7df8e5b87802d254c86ed))
* add GET upgraded packages endpoint ([c1b366e](https://github.com/prezdev88/pacman-api/commit/c1b366e72d9bcf3c425650080b7d41ca04f2530b))
* add getPackageBy packageGroupName (index and script files too) ([0c0c28e](https://github.com/prezdev88/pacman-api/commit/0c0c28e07f4bccf1abc1c27f7d0d9ae6968d0918))
* add groups features in index.html ([305f5ac](https://github.com/prezdev88/pacman-api/commit/305f5ac5247bb5b5523b43f2c1e9090926c43ced))
* add Package model class. Refactor notes file ([f563b7b](https://github.com/prezdev88/pacman-api/commit/f563b7bf1bff34cff4b17a543ea1fd9c8efa112f))
* add PackageNotFoundException and PackageNotFoundExceptionHandler ([f9b4cf6](https://github.com/prezdev88/pacman-api/commit/f9b4cf62b31ff3050661b94c9a050f47384b870e))
* add run.sh file ([aafd19b](https://github.com/prezdev88/pacman-api/commit/aafd19b429999618f19195ee8abedbab4afc9ee8))
* add standard version ([62bba21](https://github.com/prezdev88/pacman-api/commit/62bba218ceb96300030887689d98fc27e5cdf77d))
* add swagger ui ([60c6afc](https://github.com/prezdev88/pacman-api/commit/60c6afc0c74f3dc20b8c3a662e39cf59136b0965))
* add TimeUtil to log time ([a0a04dd](https://github.com/prezdev88/pacman-api/commit/a0a04ddf67f020541017db4f50670a22cfd53875))
* add two endpoints: get groups and get package names by group name ([836b2f5](https://github.com/prezdev88/pacman-api/commit/836b2f5fa39f9269ebd6b32d58e3a2c1a1876fed))
* add WrongPasswordException and NoPackagesToUpgradeException. ([d6b461f](https://github.com/prezdev88/pacman-api/commit/d6b461fee2c4bb41ce774ca81562eec0875d9718))
* first commit. add notes.md file ([1da25b0](https://github.com/prezdev88/pacman-api/commit/1da25b06b8a48d9246b8f37075a333f8039e58b8))
* **getInstalledPackages:** wip ([3272de7](https://github.com/prezdev88/pacman-api/commit/3272de7a05d162d3f7cb104ecb1a0b723b2f2c85))


### Bug Fixes

* fix package name. Add UtilService ([13fc45f](https://github.com/prezdev88/pacman-api/commit/13fc45f32fef65604cd27b9ccfd4cd26eb19d33b))


### Refactor

* .gitignore, add *.gz ([c250d39](https://github.com/prezdev88/pacman-api/commit/c250d39b67d8eaad59a25f2343737b28fbc87667))
* add cross origins to all controllers ([fdb400d](https://github.com/prezdev88/pacman-api/commit/fdb400d598597e4e26b3013bce5a0dd49e37e511))
* add groups and packages js files. Delete native and foreign files ([549e331](https://github.com/prezdev88/pacman-api/commit/549e3319779a79c72603d15b0c1bf956574e0c57))
* add http responses to README.md ([39c248d](https://github.com/prezdev88/pacman-api/commit/39c248d0aab523952b6a69d2f082f7bed08d37b1))
* add native and foreign endpoints to index.html. Separate script.js (native and foreign js file) ([f910d4a](https://github.com/prezdev88/pacman-api/commit/f910d4a6d86ce5f6b29716db641eac24bf652e78))
* add PackageMapper ([33eb04e](https://github.com/prezdev88/pacman-api/commit/33eb04eaa8232dad77d62e4da1b6982d0b024725))
* clean code ([a684ecf](https://github.com/prezdev88/pacman-api/commit/a684ecf3b7ebccee595f9227176784e8b0719afa))
* finish getInstalledPackages (not clean code yet) ([69afce7](https://github.com/prezdev88/pacman-api/commit/69afce7de3afc9602566d06950070e996df2ced5))
* **notes:** add todo's ([a2f570e](https://github.com/prezdev88/pacman-api/commit/a2f570eb5dac5847ecc1f20f6d3d647ab2f941c4))
* **notes:** update notes ([4e52344](https://github.com/prezdev88/pacman-api/commit/4e523447b5764442f28d067796e712d243b2dba3))
* orde packages, add models and service ([4f6b066](https://github.com/prezdev88/pacman-api/commit/4f6b066795668ee98303a5bfa41274da2a8a9007))
* PacmanServiceImpl -> NativePackageServiceImpl ([369a073](https://github.com/prezdev88/pacman-api/commit/369a073c04b339b3a38b2a29b4800a9e2cf26bcc))
* reduce sonar lint issues. Change variable names ([f703a7d](https://github.com/prezdev88/pacman-api/commit/f703a7de8c70477e528f264b51f1ae602ea37601))
* refactor application.properties, add logging properties ([4d5b378](https://github.com/prezdev88/pacman-api/commit/4d5b3782536939d653f260443708c29c40271a64))
* refactor UpgradedPackage. add UpdatedAt field ([3e32301](https://github.com/prezdev88/pacman-api/commit/3e3230168cc95e38ee07c32a42db9e1b4c344bf4))
* rename main class (DemoApplication to PacmanApiApplication). Delete tests ([85d5f2c](https://github.com/prezdev88/pacman-api/commit/85d5f2c63dffdf83f349328680ccc901dc143202))
