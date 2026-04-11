package kr.eme.prcMission.listeners

import kr.eme.prcMission.managers.HudManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerConnectionListener : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        HudManager.show(e.player)
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        HudManager.hide(e.player)
    }
}
