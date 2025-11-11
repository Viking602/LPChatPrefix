package com.example.lpchatprefix;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue ENABLE_PREFIX;
    public static final ModConfigSpec.BooleanValue ENABLE_SUFFIX;
    public static final ModConfigSpec.ConfigValue<String> MESSAGE_FORMAT;
    public static final ModConfigSpec.ConfigValue<String> MESSAGE_COLOR;
    public static final ModConfigSpec.ConfigValue<String> NAME_COLOR;

    static {
        BUILDER.push("LPChatPrefix Config");

        ENABLE_PREFIX = BUILDER
                .comment("是否启用前缀显示")
                .define("enablePrefix", true);

        ENABLE_SUFFIX = BUILDER
                .comment("是否启用后缀显示")
                .define("enableSuffix", true);

        MESSAGE_FORMAT = BUILDER
                .comment("聊天消息格式模板 (支持: {prefix}, {player}, {suffix}, {message})")
                .define("messageFormat", "{prefix}{player}{suffix}: {message}");

        MESSAGE_COLOR = BUILDER
                .comment("消息文本颜色 (Minecraft 颜色代码，如: gray, white, aqua)")
                .define("messageColor", "gray");

        NAME_COLOR = BUILDER
                .comment("玩家名称颜色 (Minecraft 颜色代码)")
                .define("nameColor", "white");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
