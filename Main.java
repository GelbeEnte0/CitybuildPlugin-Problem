package de.gelbeente.citybuildPlugin;

import de.gelbeente.citybuildPlugin.commands.*;
import de.gelbeente.citybuildPlugin.commands.CodeCommand;
import de.gelbeente.citybuildPlugin.listeners.ShopListener;
import de.gelbeente.citybuildPlugin.listeners.ChatListener;
import de.gelbeente.citybuildPlugin.listeners.ElevatorListener;
import de.gelbeente.citybuildPlugin.listeners.AdminPanelListener;
import de.gelbeente.citybuildPlugin.listeners.CrateListener;
import de.gelbeente.citybuildPlugin.listeners.EnchantListener;
import de.gelbeente.citybuildPlugin.listeners.JobListener;
import de.gelbeente.citybuildPlugin.listeners.PerkListener;
import de.gelbeente.citybuildPlugin.listeners.TradeListener;
import de.gelbeente.citybuildPlugin.manager.EconomyManager;
import de.gelbeente.citybuildPlugin.manager.MarketManager;
import de.gelbeente.citybuildPlugin.manager.RankManager;
import de.gelbeente.citybuildPlugin.manager.ScoreboardManager;
import de.gelbeente.citybuildPlugin.manager.SellManager;
import de.gelbeente.citybuildPlugin.manager.CrateManager;
import de.gelbeente.citybuildPlugin.manager.EnchantManager;
import de.gelbeente.citybuildPlugin.manager.JobManager;
import de.gelbeente.citybuildPlugin.manager.BattlePassManager;
import de.gelbeente.citybuildPlugin.manager.PerkManager;
import de.gelbeente.citybuildPlugin.manager.ClanManager;
import de.gelbeente.citybuildPlugin.manager.TradeManager;
import de.gelbeente.citybuildPlugin.manager.BoosterManager;
import de.gelbeente.citybuildPlugin.manager.ShopManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private EconomyManager economyManager;
    private ScoreboardManager scoreboardManager;
    private ShopManager shopManager;
    private RankManager rankManager;
    private SellManager sellManager;
    private MarketManager marketManager;
    private CrateManager crateManager;
    private EnchantManager enchantManager;
    private JobManager jobManager;
    private BattlePassManager battlePassManager;
    private PerkManager perkManager;
    private ClanManager clanManager;
    private TradeManager tradeManager;
    private BoosterManager boosterManager;

    private boolean chatMuted = false;

    @Override
    public void onEnable() {
        this.economyManager = new EconomyManager(this);
        this.rankManager = new RankManager();
        this.scoreboardManager = new ScoreboardManager(this);
        this.shopManager = new ShopManager(this);
        this.sellManager = new SellManager(this);
        this.marketManager = new MarketManager(this);
        this.crateManager = new CrateManager(this);
        this.enchantManager = new EnchantManager();
        this.jobManager = new JobManager(this);
        this.battlePassManager = new BattlePassManager(this);
        this.perkManager = new PerkManager(this);
        this.clanManager = new ClanManager(this);
        this.tradeManager = new TradeManager();
        this.boosterManager = new BoosterManager(this);
        this.scoreboardManager.startScoreboardTask();

        getCommand("money").setExecutor(new MoneyCommand(this));
        getCommand("bank").setExecutor(new BankCommand(this));
        getCommand("sign").setExecutor(new SignCommand());
        getCommand("shop").setExecutor(new ShopCommand(this, shopManager));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("sell").setExecutor(new SellCommand(this, sellManager));
        getCommand("adminpanel").setExecutor(new AdminPanelCommand());
        getCommand("verlosung").setExecutor(new GiveawayCommand());
        getCommand("poll").setExecutor(new PollCommand());
        getCommand("wb").setExecutor(new WorkbenchCommand());
        getCommand("repair").setExecutor(new RepairCommand());
        getCommand("code").setExecutor(new CodeCommand(economyManager));
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("cf").setExecutor(new CoinflipCommand(this));
        getCommand("signall").setExecutor(new SignAllCommand());
        getCommand("crate").setExecutor(new CrateCommand(this));
        getCommand("adminverzauberung").setExecutor(new EnchantCommand(this));
        getCommand("jobs").setExecutor(new JobsCommand(this));
        getCommand("bp").setExecutor(new BattlePassCommand(this));
        getCommand("rank").setExecutor(new RankCommand(this));
        PerksCommand perksCommand = new PerksCommand(this);
        getCommand("perks").setExecutor(perksCommand);

        getCommand("clan").setExecutor(new ClanCommand(this));
        getCommand("trade").setExecutor(new TradeCommand(this));
        getCommand("booster").setExecutor(new BoosterCommand(this));
        getCommand("report").setExecutor(new ReportCommand());

        Bukkit.getPluginManager().registerEvents(new ShopListener(this, shopManager), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this, rankManager), this);
        Bukkit.getPluginManager().registerEvents(new de.gelbeente.citybuildPlugin.SellListener(this, sellManager), this);
        Bukkit.getPluginManager().registerEvents(new CrateListener(this), this);
        Bukkit.getPluginManager().registerEvents(new AdminPanelListener(), this);
        Bukkit.getPluginManager().registerEvents(new ElevatorListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnchantListener(this, enchantManager), this);
        Bukkit.getPluginManager().registerEvents(new JobListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PerkListener(this), this);
        Bukkit.getPluginManager().registerEvents(perksCommand, this); // GUI Click Event
        Bukkit.getPluginManager().registerEvents(new TradeListener(), this);
    }

    @Override
    public void onDisable() {
        if (economyManager != null) {
            economyManager.save();
        }
        if (shopManager != null) {
            shopManager.save();
        }
        if (crateManager != null) {
            crateManager.save();
        }
    }

    public EconomyManager getEconomyManager() {
        return economyManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public RankManager getRankManager() {
        return rankManager;
    }

    public MarketManager getMarketManager() {
        return marketManager;
    }

    public CrateManager getCrateManager() {
        return crateManager;
    }

    public EnchantManager getEnchantManager() {
        return enchantManager;
    }

    public JobManager getJobManager() {
        return jobManager;
    }

    public BattlePassManager getBattlePassManager() {
        return battlePassManager;
    }

    public PerkManager getPerkManager() {
        return perkManager;
    }

    public ClanManager getClanManager() {
        return clanManager;
    }

    public TradeManager getTradeManager() {
        return tradeManager;
    }

    public BoosterManager getBoosterManager() {
        return boosterManager;
    }

    public boolean isChatMuted() {
        return chatMuted;
    }

    public void setChatMuted(boolean chatMuted) {
        this.chatMuted = chatMuted;
    }

    public SellManager getSellManager() {
        return null;
    }

    public ShopManager getShopManager() {
        return shopManager;
    }
}