# LuckPerms Chat Prefix for Forge

[![GitHub Release](https://img.shields.io/github/release/Viking602/LPchatprefix.svg)](https://github.com/Viking602/LPchatprefix/releases/latest)
[![Downloads](https://img.shields.io/github/downloads/Viking602/LPchatprefix/total.svg)](https://github.com/Viking602/LPchatprefix/releases)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green.svg)](https://minecraft.net)
[![Forge](https://img.shields.io/badge/Forge-47.1.0+-orange.svg)](https://files.minecraftforge.net)

A server-side Forge mod that displays LuckPerms prefixes and suffixes in chat messages for Minecraft 1.20.1.

## Features

- 🏷️ Display LuckPerms prefixes and suffixes in chat
- 🎨 Support for Minecraft color codes (& symbols)
- ⚙️ Highly configurable message format
- 🖥️ Server-side only - clients don't need to install
- 🔧 Lightweight and efficient

## Requirements

- Minecraft 1.20.1 (strictly this version only)
- Forge 47.1.0 or higher
- LuckPerms plugin installed on server
- Java 17 or higher

## Installation

### Download
Download the latest version from [GitHub Releases](https://github.com/Viking602/LPchatprefix/releases/latest)

### Server Installation
1. Download the latest `lpchatprefix-1.20.1_1.1.0.jar` from [releases page](https://github.com/Viking602/LPchatprefix/releases)
2. Place the JAR file in your server's `mods` folder
3. Ensure LuckPerms is installed and configured
4. Start/restart your server

### Client Installation
**Not required!** This is a server-side only mod. Players can connect without installing anything.

## Configuration

The mod creates a configuration file at `config/lpchatprefix-common.toml` with the following options:

```toml
[LPChatPrefix Config]
    # Enable/disable prefix display
    enablePrefix = true
    
    # Enable/disable suffix display
    enableSuffix = true
    
    # Chat message format template
    # Supported placeholders: {prefix}, {player}, {suffix}, {message}
    messageFormat = "{prefix}{player}{suffix}: {message}"
    
    # Message text color (Minecraft color codes)
    # Options: black, dark_blue, dark_green, dark_aqua, dark_red, dark_purple, 
    # gold, gray, dark_gray, blue, green, aqua, red, light_purple, yellow, white
    messageColor = "gray"
    
    # Player name color
    nameColor = "white"
```

## Usage

1. Set up prefixes/suffixes in LuckPerms:
```bash
# Add a prefix to a group
/lp group admin meta setprefix "&c[Admin] "

# Add a suffix to a user
/lp user <player> meta setsuffix " &7[VIP]"
```

2. The prefixes and suffixes will automatically appear in chat messages

### Example Output
Without mod: `<PlayerName> Hello everyone!`

With mod: `[Admin] PlayerName [VIP]: Hello everyone!`

## Color Code Support

The mod supports Minecraft color codes using the `&` symbol:
- `&c` - Red
- `&a` - Green
- `&b` - Aqua
- `&e` - Yellow
- `&f` - White
- And all other standard Minecraft color codes

## Troubleshooting

### Prefixes/Suffixes not showing
- Ensure LuckPerms is installed and running
- Check that the player/group has prefixes/suffixes set
- Verify the mod is loaded (check server logs for "LPChatPrefix initialized")

### Double player names in chat
- Make sure you're using the latest version
- Check for conflicting chat formatting plugins/mods

### Mod not loading
- Verify you're using Minecraft 1.20.1 exactly
- Ensure Forge version is 47.1.0 or higher
- Check Java version is 17 or higher

## Building from Source

```bash
# Clone the repository
git clone https://github.com/Viking602/LPchatprefix.git
cd LPchatprefix

# Build the mod
./gradlew clean build

# Output JAR will be in build/libs/
# File: lpchatprefix-1.20.1_1.1.0.jar
```

## Releases

Releases are automatically built and published when a new tag is pushed:
```bash
# Create and push a version tag
git tag v1.20.1_1.1.0
git push origin v1.20.1_1.1.0
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.

## Credits

- Built for Forge
- Requires LuckPerms by Luck
- Developed for Minecraft 1.20.1
