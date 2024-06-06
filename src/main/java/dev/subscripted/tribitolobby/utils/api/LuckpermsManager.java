package dev.subscripted.tribitolobby.utils.api;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;

import java.util.UUID;

public class LuckpermsManager {

    private final LuckPerms api = LuckPermsProvider.get();

    public String getPlayerGroup(UUID playerUUID) {
        User user = api.getUserManager().getUser(playerUUID);
        if (user == null) {
            return "User not found"; // Oder eine andere sinnvolle Standardnachricht
        }

        ContextSet contexts = api.getContextManager().getContext(user).orElse(null);
        if (contexts == null) {
            return "No context"; // Oder eine andere sinnvolle Standardnachricht
        }

        String prefix = user.getCachedData().getMetaData(QueryOptions.contextual(contexts)).getPrefix();
        if (prefix == null) {
            return "No prefix"; // Oder eine andere sinnvolle Standardnachricht
        }

        return prefix;
    }
}
