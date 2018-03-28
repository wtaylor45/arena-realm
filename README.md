# GLADIUS
A new realm where players work together to fight against waves of monsters, new and old.

## Setup Instructions
This will walk you through setting up your development environment for use with an AWS EC2 instance. This will *not* walk through setting up the EC2 instance with Minecraft Forge! 

### Prerequistes 
* Minecraft Forge 1.12.2: [https://files.minecraftforge.net/](https://files.minecraftforge.net/)
* Eclipse: [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/)
* Git BASH (Windows Only): {https://git-scm.com/downloads](https://git-scm.com/downloads)

### Steps
1. Clone this repository.
2. From the terminal, access the root directory of the repository `./setup.sh`. Run the setup file which will ask you for a few things:
  1. The key file used to access your AWS instance
  2. The full or relative path to your client's `mods` folder
  3. The Public DNS of the EC2 instance you'll be using
  4. The full path to the server's `mods` folder
