package kr.eme.prcMission.api

import kr.eme.prcMission.enums.MissionVersion
import kr.eme.prcMission.managers.HudManager
import kr.eme.prcMission.managers.MissionManager
import kr.eme.prcMission.managers.MissionStateManager

/**
 * PRCMission 외부 API.
 * 다른 플러그인에서 미션 상태를 조회하거나 미션을 시작할 수 있습니다.
 *
 * 사용 예시 (Java):
 * ```java
 * int missionNumber = MissionAPI.INSTANCE.getCurrentMissionNumber(MissionVersion.V1);
 * boolean started = MissionAPI.INSTANCE.startMission(MissionVersion.V1);
 * ```
 *
 * 사용 예시 (Kotlin):
 * ```kotlin
 * val missionNumber = MissionAPI.getCurrentMissionNumber(MissionVersion.V1)
 * val started = MissionAPI.startMission(MissionVersion.V1)
 * ```
 */
object MissionAPI {

    /**
     * 현재 진행 중인 미션 번호(ID)를 반환합니다.
     *
     * @param version 미션 버전 (V1, V2)
     * @return 현재 미션 ID (1~20), 미수락 상태면 0, 모든 미션 완료면 -1
     */
    fun getCurrentMissionNumber(version: MissionVersion): Int {
        val index = MissionStateManager.getCurrentIndex(version)
        if (index == -1) return 0 // 미수락 상태

        val missions = MissionManager.getMissions(version)
        if (index >= missions.size) return -1 // 모든 미션 완료

        return missions[index].id
    }

    /**
     * 현재 활성화된 미션의 버전과 번호를 반환합니다.
     * V1부터 순서대로 확인하여 진행 중인 미션을 찾습니다.
     *
     * @return Pair(버전, 미션ID) 또는 null (모든 미션 완료/미수락)
     */
    fun getActiveMission(): Pair<MissionVersion, Int>? {
        for (version in MissionVersion.entries) {
            if (!MissionStateManager.canStart(version)) continue
            if (MissionStateManager.isVersionCleared(version)) continue
            val index = MissionStateManager.getCurrentIndex(version)
            if (index < 0) continue
            val mission = MissionManager.getCurrent(version, index) ?: continue
            return version to mission.id
        }
        return null
    }

    /**
     * 해당 버전의 첫 번째 미션을 시작(수락)합니다.
     * 이미 시작된 상태라면 false를 반환합니다.
     *
     * @param version 미션 버전 (V1, V2)
     * @return 시작 성공 여부
     */
    fun startMission(version: MissionVersion): Boolean {
        if (!MissionStateManager.canStart(version)) return false

        val started = MissionStateManager.acceptFirst(version)
        if (started) {
            HudManager.refreshAll()
        }
        return started
    }
}
