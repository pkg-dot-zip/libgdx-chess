<p align="center">
  <a href="https://github.com/pkg-dot-zip/libgdx-chess" rel="noopener">
 <img width=200px height=200px src="" alt="Project logo"></a>
</p>

<h3 align="center">LibGDX-Chess</h3>

<div align="center">

  [![Stars](https://img.shields.io/github/stars/pkg-dot-zip/libgdx-chess)](https://github.com/pkg-dot-zip/libgdx-chess/stargazers)
  [![Downloads](https://img.shields.io/github/downloads/pkg-dot-zip/libgdx-chess/total)](https://github.com/pkg-dot-zip/libgdx-chess/releases)
  [![Last Commit](https://img.shields.io/github/last-commit/pkg-dot-zip/libgdx-chess)](https://github.com/pkg-dot-zip/libgdx-chess/commits/development)
  [![License](https://img.shields.io/github/license/pkg-dot-zip/libgdx-chess)](/LICENSE)

</div>

---

<p align="center"> A simple networking chess game written in Java using LibGDX.
    <br> 
</p>

## 📝 Table of Contents
- [About](#about)
- [Usage](#usage)
- [Built Using](#built_using)
- [Authors](#authors)

## 🧐 About <a name = "about"></a>
LibGDX-Chess is a multiplayer chess game made in Java, using LibGDX. This project was made in college for a networking assignment.

This application writes the entire List of ChessFields as an object and the chat messages as Strings. This is because we needed to use both
for the assignment, and I recommend real world applications to replace the object with a string in the Forsyth–Edwards Notation (FEN).

## 🎈 Usage <a name="usage"></a>
Instructions on how to get this project running on your machine:

1. Download the project from the GitHub repository.
1. Open the project in IntelliJ IDEA.
1. Open the Gradle tab on the right side of the screen and click on the "Refresh all Gradle projects" button.
1. Now go to the Server class in the project and run it.
1. Now open two instance of the game, this is done by:
    1. Open the Gradle tab on the right of the screen.
    1. Navigate to libgdx-chess (root) -> Tasks -> other.
    1. Click on "run".
    1. Now the application will run, and a build configuration will be added to IntelliJ.
    1. Go to the build configurations and click on "Edit Configurations".
    1. Enable "Allow parallel run".
    1. Run a second instance of the application by clicking on "run" again.

## ⛏️ Built Using <a name = "built_using"></a>
- [libGDX](https://libgdx.com/) - Framework
- [ShapeDrawer](https://github.com/earlygrey/shapedrawer) - Library for LibGDX

## ✍️ Authors <a name = "authors"></a>
- [@OnsPetruske](https://github.com/pkg-dot-zip) - Idea, Initial work, general development & management
- [@medkam](https://github.com/medkam) - General development
