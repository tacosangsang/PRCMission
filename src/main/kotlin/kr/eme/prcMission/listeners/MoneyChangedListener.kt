package kr.eme.prcMission.listeners

import kr.eme.prcMission.managers.HudManager
import kr.eme.prcMoney.api.events.MoneyChangedEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * PRCMoney에서 발행하는 [MoneyChangedEvent]를 구독해
 * 모든 온라인 플레이어의 미션 HUD를 즉시 갱신한다.
 *
 * MoneyManager는 글로벌 잔액(공동 EP)을 보유하므로,
 * 변경이 발생하면 전체 플레이어의 HUD를 한 번 다시 그린다.
 */
object MoneyChangedListener : Listener {
    @EventHandler
    fun onMoneyChanged(event: MoneyChangedEvent) {
        HudManager.refreshAll()
    }
}
