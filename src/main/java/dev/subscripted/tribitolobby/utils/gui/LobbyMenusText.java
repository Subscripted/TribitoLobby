package dev.subscripted.tribitolobby.utils.gui;

public enum LobbyMenusText {

    TELEPORTER_NAME("§8» §8| §a§lTeleporter §8(§7Rechtsclick§8)§r"),
    SETTINGS_NAME("§8» §8| §5§lEinstellungen §8(§7Rechtsclick§8)§r"),
    PLAYERHIDER_INACTIVE_NAME("§7» §8| §a§lPlayerhider §8[§e§lInaktiv§r§8] §8(§7Rechtsclick§8)§r"),
    PLAYERHIDER_ACTIVE_NAME("§7» §8| §c§lPlayerhider §8[§c§lAktiv§r§8] §8(§7Rechtsclick§8)§r"),
    LOBBY_MENU_TITLE("§8» §8| §2§lLobby Menu §8• AUSWAHL"),
    SERVER_SELECTOR_TITLE("§8§l» §8| §x§D§D§6§3§3§2§lS§x§D§F§5§C§3§8§le§x§E§0§5§5§3§D§lr§x§E§2§4§F§4§3§lv§x§E§3§4§8§4§8§le§x§E§5§4§1§4§E§lr §8• AUSWAHL"),
    SERVER_SELECTOR_ITEM_NAME("§8§l» §8| §x§D§D§6§3§3§2§lS§x§D§F§5§C§3§8§le§x§E§0§5§5§3§D§lr§x§E§2§4§F§4§3§lv§x§E§3§4§8§4§8§le§x§E§5§4§1§4§E§lr"),
    SERVER_SELECTOR_ITEM_LORE_LINE_2("§8» §7Drücke §eLinksklick §7um in die Serverauswahl zu kommen."),
    EMPTY_LORE(" ");


    private final String text;

    LobbyMenusText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
