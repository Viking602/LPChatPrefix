# LuckPerms 聊天前缀 NeoForge 模组

[![GitHub 发布](https://img.shields.io/github/release/YOUR_GITHUB_USERNAME/lpchatprefix.svg)](https://github.com/YOUR_GITHUB_USERNAME/lpchatprefix/releases/latest)
[![下载量](https://img.shields.io/github/downloads/YOUR_GITHUB_USERNAME/lpchatprefix/total.svg)](https://github.com/YOUR_GITHUB_USERNAME/lpchatprefix/releases)
[![许可证](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://minecraft.net)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.0+-orange.svg)](https://neoforged.net)

一个服务器端 NeoForge 模组，用于在 Minecraft 1.21.1 的聊天消息中显示 LuckPerms 的前缀和后缀。

[English](README.md) | **中文**

## 功能特性

- 🏷️ 在聊天中显示 LuckPerms 前缀和后缀
- 🎨 支持 Minecraft 颜色代码（& 符号）
- ⚙️ 高度可配置的消息格式
- 🖥️ 仅服务器端 - 客户端无需安装
- 🔧 轻量且高效

## 系统要求

- Minecraft 1.21.1（严格限定此版本）
- NeoForge 21.1.0 或更高版本
- 服务器已安装 LuckPerms 插件
- Java 21 或更高版本

## 安装说明

### 下载
从 [GitHub Releases](https://github.com/YOUR_GITHUB_USERNAME/lpchatprefix/releases/latest) 下载最新版本

### 服务器安装
1. 从[发布页面](https://github.com/YOUR_GITHUB_USERNAME/lpchatprefix/releases)下载最新的 `lpchatprefix-1.21.1_1.1.0.jar`
2. 将 JAR 文件放入服务器的 `mods` 文件夹
3. 确保 LuckPerms 已安装并配置
4. 启动/重启服务器

### 客户端安装
**无需安装！** 这是一个纯服务器端模组，玩家无需安装任何东西即可连接。

## 配置说明

模组会在 `config/lpchatprefix-common.toml` 创建配置文件，包含以下选项：

```toml
[LPChatPrefix Config]
    # 启用/禁用前缀显示
    enablePrefix = true
    
    # 启用/禁用后缀显示
    enableSuffix = true
    
    # 聊天消息格式模板
    # 支持的占位符：{prefix}, {player}, {suffix}, {message}
    messageFormat = "{prefix}{player}{suffix}: {message}"
    
    # 消息文本颜色（Minecraft 颜色代码）
    # 可选：black, dark_blue, dark_green, dark_aqua, dark_red, dark_purple, 
    # gold, gray, dark_gray, blue, green, aqua, red, light_purple, yellow, white
    messageColor = "gray"
    
    # 玩家名称颜色
    nameColor = "white"
```

## 使用方法

1. 在 LuckPerms 中设置前缀/后缀：
```bash
# 为组添加前缀
/lp group admin meta setprefix "&c[管理员] "

# 为用户添加后缀
/lp user <玩家名> meta setsuffix " &7[VIP]"
```

2. 前缀和后缀将自动显示在聊天消息中

### 示例效果
没有模组：`<玩家名> 大家好！`

使用模组：`[管理员] 玩家名 [VIP]: 大家好！`

## 颜色代码支持

模组支持使用 `&` 符号的 Minecraft 颜色代码：
- `&c` - 红色
- `&a` - 绿色
- `&b` - 青色
- `&e` - 黄色
- `&f` - 白色
- 以及所有其他标准 Minecraft 颜色代码

## 故障排除

### 前缀/后缀不显示
- 确保 LuckPerms 已安装并运行
- 检查玩家/组是否已设置前缀/后缀
- 验证模组已加载（查看服务器日志中的 "LPChatPrefix initialized"）

### 聊天中出现重复的玩家名
- 确保使用最新版本
- 检查是否有冲突的聊天格式化插件/模组

### 模组无法加载
- 验证是否使用 Minecraft 1.21.1 版本
- 确保 NeoForge 版本为 21.1.0 或更高
- 检查 Java 版本是否为 21 或更高

## 从源代码构建

```bash
# 克隆仓库
git clone https://github.com/YOUR_GITHUB_USERNAME/lpchatprefix.git
cd lpchatprefix

# 构建模组
./gradlew clean build

# 输出的 JAR 文件将在 build/libs/ 目录中
# 文件：lpchatprefix-1.21.1_1.1.0.jar
```

## 版本发布

当推送新的标签时，会自动构建并发布版本：
```bash
# 创建并推送版本标签
git tag v1.21.1_1.1.0
git push origin v1.21.1_1.1.0
```

## 许可证

本项目采用 MIT 许可证 - 详见 LICENSE 文件。

## 贡献

欢迎贡献！请随时提交 Pull Request。

## 支持

如有问题、疑问或建议，请在 GitHub 仓库中提交 issue。

## 致谢

- 基于 NeoForge 构建
- 需要 Luck 开发的 LuckPerms
- 专为 Minecraft 1.21.1 开发
