# PetAppKMP
> A Kotlin Multiplatform Project - Android and Desktop

## Preview Video
[![PET APP VIDEO](screenshot/preview.png)](https://www.youtube.com/watch?v=mX7R_xBhAfc)

## Features
- Fetch pets from [PetFinder API](https://www.petfinder.com/developers/v2/docs/#get-animals)
- Display display all pets with infinity scroll in desktop and android
- Load pets images using Coil
- Clean MVVM architecture
- Dependency injection with Koin
- Shared some code and screen between Android and Desktop using KMM to show how versatile is KMP
- Also share resources between platforms
- Create separated ui code and instances for both platform (Android and Desktop) when shared code is not useful


## Tech Stack
- **Kotlin Multiplatform (KMP)**: Share business logic between Android and Desktop
- **Ktor**: HTTP client for API communication
- **Coil**: Image loading for Android
- **Koin**: Dependency injection
- **MVVM Architecture**: Clean and scalable code structure


## Why KMP ?
> Because with kmp you can share all of the core with multiple projects and interfaces easily. If you have a POS app, you can use the same core create your sales point in a customer selfcheckout, android and desktop with any interface.
![Project Architecture](screenshot/kmp_arch.png)


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


- This project was created using Petfinder Api - [PetFinder API](https://www.petfinder.com/developers/v2/docs/#get-animals)
- Figma template - [FIGMA TEMPLATE](https://www.figma.com/community/file/1164046882633361201/free-template-ecommerce-website-monito-pets-for-best-community)


**Developed by Jonathan Souza**

[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/jonathan-souza-6b570338/)