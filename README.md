🏡 Real Estate Application
=============

🎯 **An Android application for real estate listings**

🎥 Demo
--------------------
https://github.com/user-attachments/assets/c867abcb-a84d-4bb4-a5e5-c6352b5a8092

📁 Project Structure
--------------------

```
📦 pexels/
├── 📂 app/                         # Main application module
├── 📂 core/                        # Core utilities and shared logic
│      └── 📂 common/               # Common module shared between modules
│      └── 📂 data/                 # Data module which provides repositories, paging sources
│      └── 📂 database/             # Database module
│      └── 📂 datastore/            # Datastore module    
│      └── 📂 domain/               # Domain module which provides use cases, repositories
│      └── 📂 model/                # Model module
│      └── 📂 network/              # Network module
│      └── 📂 testing/              # Testing module
├── 📂 features/                    # Features module
│      └── 📂 listproperties/       # Real estate list feature
└── 📂 gradle/                      # Gradle wrapper and build scripts

```

🔧 Prerequisites
--------------

- 🧑‍💻 **Android Studio**: [Meerkat](https://developer.android.com/studio)
- 🛠️ **Android Gradle Plugin**: 8.9.0
- ⚙️ **Gradle**: 8.13

✨ Key Features
--------------

- 🏘️ Property Listings with Pagination — Browse a large catalog of real estate properties efficiently using Paging 3.
- ⭐ Bookmarks — Mark favorite properties and access them anytime, even offline.
- 📶 Offline-first — Property Listings & bookmarked are stored locally to ensure availability offline.
- 🗂️ Modular Architecture — Designed for scalability with clean architecture principles.

🏗️ How to Build
--------------

1. Clone the repository:
   ```bash
   git clone https://github.com/vanxioussss/SMG.git
   ```

2. Sync the build project as usual.

📦APK File
--------------

In case you can't build the project, \
Check the release [here](https://github.com/vanxioussss/SMG/releases/tag/v1.0.0)

🛠️ Tech Stack
--------------

- 🧑‍💻 **Language**: ![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-blue?logo=kotlin&logoColor=white)

- 🏗️ **Architecture**: Clean architecture - Single Activity - MVVM Design pattern - Multi-moduled

- 📦 **Libraries**:
    - **Compose**: [Jetpack Compose](https://developer.android.com/compose)

    - **Navigation**: [Navigation Component](https://developer.android.com/guide/navigation)

    - **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)

    - **Networking**: [Retrofit](https://square.github.io/retrofit/)

    - **Database**: [Room](https://developer.android.com/jetpack/androidx/releases/room)

    - **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

    - **Reactive Streams**: [Flow](https://kotlinlang.org/docs/flow.html)

    - **Pagingination**: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)

    - **Unit testing**: [JUnit](https://junit.org/junit4/), [Mockito](https://site.mockito.org/), [Turbine](https://code.cash.app/flow-testing-with-turbine),
      etc

🧠 Decision Making
-------------
This section outlines the rationale behind key architectural and UX decisions made during the
development of this application.

🏗️ **Architecture**:

 - **Clean Architecture + MVVM** \
  Ensures clear separation of concerns across layers: UI, Domain, and Data. Each layer has a
  well-defined responsibility, improving testability and maintainability.

 - **Modularization** \
  Even though the app requirements are simple, the project is modularized to support scalability.
  This allows different features to be developed in isolation without conflicts, making the codebase
  future-proof and team-friendly.

 - **Single Activity with Jetpack Compose & Navigation Compose** \
  The app uses a single-activity architecture, with features separted into submodules. This
  centralizes navigation and simplifies lifecycle handling across screens.
  Even if there is only one feature now, Navigation will help for future growth of the project.

 - **Offline-first with Pagination and RemoteMediator** \
   Even though the current API does not support pagination, Paging 3 with RemoteMediator is
   already integrated into the architecture to ensure offline-first behavior and future scalability.

🚀 Future Improvements
---------------
Some planned or possible enhancements:

**Improve image loading performance** \
Currently the time to load each image is still high.

**Support Build variants** \
Due to time constraint, I don't have enough time to support build variance but it will be a good
improvements in the future.

**Improve Code quality**
