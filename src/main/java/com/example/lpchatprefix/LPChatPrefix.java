package com.example.lpchatprefix;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.api.distmarker.Dist;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerPlayer;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.cacheddata.CachedMetaData;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(LPChatPrefix.MOD_ID)
public class LPChatPrefix {
    public static final String MOD_ID = "lpchatprefix";
    private static final Logger LOGGER = LogUtils.getLogger();

    private LuckPerms luckPerms;

    public LPChatPrefix(IEventBus modEventBus, ModContainer modContainer) {
        // 只在服务器端初始化
        if (FMLEnvironment.dist == Dist.CLIENT) {
            LOGGER.info("LPChatPrefix is a server-only mod, skipping client initialization");
            return;
        }
        
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.register(this);
        LOGGER.info("LPChatPrefix initialized on server side");
    }

    @SubscribeEvent
    public void onChat(ServerChatEvent event) {
        try {
            ServerPlayer player = event.getPlayer();

            // 懒加载 LuckPerms API
            if (luckPerms == null) {
                try {
                    luckPerms = LuckPermsProvider.get();
                    LOGGER.info("LuckPerms API loaded successfully");
                } catch (IllegalStateException e) {
                    LOGGER.warn("LuckPerms not available, chat prefix will not be applied");
                    return;
                }
            }

            User user = luckPerms.getUserManager().getUser(player.getUUID());
            if (user == null) {
                LOGGER.debug("User data not found for: {}", player.getName().getString());
                return;
            }

            CachedMetaData meta = user.getCachedData().getMetaData();

            String prefix = meta.getPrefix();
            String suffix = meta.getSuffix();

            // 应用配置
            if (!Config.ENABLE_PREFIX.get()) prefix = null;
            if (!Config.ENABLE_SUFFIX.get()) suffix = null;

            if (prefix == null) prefix = "";
            if (suffix == null) suffix = "";

            // 转换颜色代码
            prefix = convertColorCodes(prefix);
            suffix = convertColorCodes(suffix);

            // 解析组件
            Component prefixComp = parseMinecraftComponent(prefix);
            Component suffixComp = parseMinecraftComponent(suffix);

            // 获取配置的颜色
            ChatFormatting nameColor = getChatFormatting(Config.NAME_COLOR.get());
            ChatFormatting messageColor = getChatFormatting(Config.MESSAGE_COLOR.get());

            Component playerComp = Component.literal(player.getName().getString()).withStyle(nameColor);
            Component messageComp = Component.literal(event.getRawText()).withStyle(messageColor);

            // 构建最终消息
            String format = Config.MESSAGE_FORMAT.get();
            Component finalMsg = buildFormattedMessage(format, prefixComp, playerComp, suffixComp, messageComp);

            // 取消原始事件，发送自定义消息
            event.setCanceled(true);
            
            // 广播自定义格式的消息给所有玩家
            player.getServer().getPlayerList().broadcastSystemMessage(finalMsg, false);

        } catch (Exception e) {
            LOGGER.error("Error processing chat message", e);
        }
    }

    private Component buildFormattedMessage(String format, Component prefix, Component player, Component suffix, Component message) {
        net.minecraft.network.chat.MutableComponent result = Component.empty();
        
        // 简单的模板替换实现
        if (format.contains("{prefix}")) {
            String[] parts = format.split("\\{prefix\\}", 2);
            if (!parts[0].isEmpty()) result = result.append(Component.literal(parts[0]));
            result = result.append(prefix);
            format = parts.length > 1 ? parts[1] : "";
        }
        
        if (format.contains("{player}")) {
            String[] parts = format.split("\\{player\\}", 2);
            if (!parts[0].isEmpty()) result = result.append(Component.literal(parts[0]));
            result = result.append(player);
            format = parts.length > 1 ? parts[1] : "";
        }
        
        if (format.contains("{suffix}")) {
            String[] parts = format.split("\\{suffix\\}", 2);
            if (!parts[0].isEmpty()) result = result.append(Component.literal(parts[0]));
            result = result.append(suffix);
            format = parts.length > 1 ? parts[1] : "";
        }
        
        if (format.contains("{message}")) {
            String[] parts = format.split("\\{message\\}", 2);
            if (!parts[0].isEmpty()) result = result.append(Component.literal(parts[0]));
            result = result.append(message);
            format = parts.length > 1 ? parts[1] : "";
        }
        
        if (!format.isEmpty()) {
            result = result.append(Component.literal(format));
        }
        
        return result;
    }

    private String convertColorCodes(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.replace('&', '§');
    }

    private Component parseMinecraftComponent(String text) {
        if (text == null || text.isEmpty()) return Component.empty();
        
        try {
            // 直接使用 Minecraft 的 Component.Serializer
            return Component.literal(text);
        } catch (Exception e) {
            LOGGER.warn("Failed to parse component: {}", text, e);
            return Component.literal(text);
        }
    }

    private ChatFormatting getChatFormatting(String colorName) {
        if (colorName == null || colorName.isEmpty()) return ChatFormatting.WHITE;
        
        try {
            return ChatFormatting.valueOf(colorName.toUpperCase());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Invalid color name: {}, using WHITE", colorName);
            return ChatFormatting.WHITE;
        }
    }
}
