package kr.eme.prcMission.objects.missions

import kr.eme.prcMission.objects.models.Mission
import kr.eme.prcMission.objects.const.MissionStringV2 as MS
import kr.eme.prcMission.objects.missions.conditionPresets.ConditionPresetV2 as CP
import kr.eme.prcMission.objects.missions.rewardPresets.RewardPresetV2 as RP


object MissionsV2 {
    val missions: List<Mission> = listOf (
        Mission(
            1,
            MS.m1_title,
            MS.m1_desc,
            CP.m1_cond,
            RP.m1_reward,
            "§f영양캡슐 5개, 성장캡슐 5개"
        ),
        Mission(
            2,
            MS.m2_title,
            MS.m2_desc,
            CP.m2_cond,
            RP.m2_reward,
            "§f철 주괴 20개, 구리 주괴 20개"
        ),
        Mission(
            3,
            MS.m3_title,
            MS.m3_desc,
            CP.m3_cond,
            RP.m3_reward,
            "§fAl-Cu 레시피, Al-Mg 레시피"
        ),
        Mission(
            4,
            MS.m4_title,
            MS.m4_desc,
            CP.m4_cond,
            RP.m4_reward,
            "§fAl-Cu 합금 16개"
        ),
        Mission(
            5,
            MS.m5_title,
            MS.m5_desc,
            CP.m5_cond,
            RP.m5_reward,
            "§fCu-Au 합금 10개"
        ),
        Mission(
            6,
            MS.m6_title,
            MS.m6_desc,
            CP.m6_cond,
            RP.m6_reward,
            "§fEP 7000"
        ),
        Mission(
            7,
            MS.m7_title,
            MS.m7_desc,
            CP.m7_cond,
            RP.m7_reward,
            "§f알루미늄 주괴 20개, 철 주괴 10개, 구리 주괴 10개"
        ),
        Mission(
            8,
            MS.m8_title,
            MS.m8_desc,
            CP.m8_cond,
            RP.m8_reward,
            "§f철 주괴 40개"
        ),
        Mission(
            9,
            MS.m9_title,
            MS.m9_desc,
            CP.m9_cond,
            RP.m9_reward,
            "§f커피잔 20개, 합금 섬유 우주복 1개"
        ),
        Mission(
            10,
            MS.m10_title,
            MS.m10_desc,
            CP.m10_cond,
            RP.m10_reward,
            "§fEP 4000"
        ),
        Mission(
            11,
            MS.m11_title,
            MS.m11_desc,
            CP.m11_cond,
            RP.m11_reward,
            "§fEP 3000"
        ),
        Mission(
            12,
            MS.m12_title,
            MS.m12_desc,
            CP.m12_cond,
            RP.m12_reward,
            "§f철 주괴 35개"
        ),
        Mission(
            13,
            MS.m13_title,
            MS.m13_desc,
            CP.m13_cond,
            RP.m13_reward,
            "§f커피머신 모듈, 합금 기어, 합금 냉각 몰드"
        ),
        Mission(
            14,
            MS.m14_title,
            MS.m14_desc,
            CP.m14_cond,
            RP.m14_reward,
            "§f구리 주괴 10개, 리튬 주괴 10개, 백금 주괴 10개, 철 주괴 10개"
        ),
        Mission(
            15,
            MS.m15_title,
            MS.m15_desc,
            CP.m15_cond,
            RP.m15_reward,
            "§f리튬 주괴 24개, 티타늄 주괴 3개"
        ),
        Mission(
            16,
            MS.m16_title,
            MS.m16_desc,
            CP.m16_cond,
            RP.m16_reward,
            "§f니켈 주괴 10개, 구리 주괴 10개"
        ),
        Mission(
            17,
            MS.m17_title,
            MS.m17_desc,
            CP.m17_cond,
            RP.m17_reward,
            "§fEP 6000"
        ),
        Mission(
            18,
            MS.m18_title,
            MS.m18_desc,
            CP.m18_cond,
            RP.m18_reward,
            "§f니켈 주괴 10개, 백금 주괴 10개, 티타늄 주괴 4개"
        ),
        Mission(
            19,
            MS.m19_title,
            MS.m19_desc,
            CP.m19_cond,
            RP.m19_reward,
            "§fEP 5000"
        ),
        Mission(
            20,
            MS.m20_title,
            MS.m20_desc,
            CP.m20_cond,
            RP.m20_reward,
            "§f엔딩 접근용 아이템"
        )
    )
}