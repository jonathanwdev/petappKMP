# PetAppKMP
> A Kotlin Multiplatform Project - Android and Desktop

## Why KMP ?
> Because with kmp you can share all of the core with multiple projects and interfaces easily. If you have a POS app, you can use the same core create your sales point in a customer selfcheckout, android and desktop with any interface.
![Project Architecture](screenshot/kmp_arch.png)

## Project Arch
1. **common**: All the shared code between platforms - Domain, Data, DI, Resources and SharedPresentation
2. **androidApp**: All android interfaces, composables and di
3. **desktopApp**: All desktop interfaces, composables and di

<code> FOLDERS
  ├── androidApp/**
  ├── desktopApp/**
  ├── common/**
  └── build.gradle  
</code>

## Screen shots

### Desktop
![SplashDesktop](screenshot/desktop_splash.gif)
![ListDesktop](screenshot/desktop_petlist.gif)
![DetailDesktop](screenshot/desktop_detail.gif)
<br>
### Mobile
![SplashMobile](screenshot/mobile_splash.gif)
![SplashMobile](screenshot/mobile_petlist.gif)
![SplashMobile](screenshot/mobile_detail.gif)
<br>

### This project was created using Petfinder Api - https://www.petfinder.com/developers/v2/docs/
### Figma template: https://www.figma.com/community/file/1164046882633361201/free-template-ecommerce-website-monito-pets-for-best-community

This is a Kotlin Multiplatform project targeting Android,  Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets. 
  - `androidMain` would be the right folder for android app.
  - `desktopMain` would be the right folder for desktop app.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…