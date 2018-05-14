package com.mtool.toolslib.model.enumList;

import android.util.Log;

/**
 * Created by JayCruz on 2017/10/2.
 */

public enum eu_LotteryType {
    BJKL8(4, "北京快乐8", eu_LotteryCategory.KL8),
    BJPK10(1, "北京赛车PK10", eu_LotteryCategory.PK10),
    QDKLSF(2, "广东快乐10分", eu_LotteryCategory.KLSF),
    QD11X5(3, "广东11选五", eu_LotteryCategory.L11X5),
    JS11X5(9, "江西11选五", eu_LotteryCategory.L11X5),

    CQSSC(6, "重庆时时彩", eu_LotteryCategory.SSC),
    TJSSC(7, "天津时时彩", eu_LotteryCategory.SSC),
    SJSSC(8, "新疆时时彩", eu_LotteryCategory.SSC),

    JSK3(10, "江苏快三", eu_LotteryCategory.K3),
    SUFT(5, "幸运飞艇", eu_LotteryCategory.SUFT);


    private int GameID;
    private String GameCode;
    private eu_LotteryCategory GameCategory;

    eu_LotteryType(int GameID, String GameCode, eu_LotteryCategory GameCategory) {
        this.GameID = GameID;
        this.GameCode = GameCode;
        this.GameCategory = GameCategory;
    }

    public static eu_LotteryCategory getCategoryByID(int gameId) {

        for (eu_LotteryType v : values())
            if (v.GameID == gameId) {
                return v.GameCategory;
            }

        Log.e("eu_LotteryType", "尚未添加该彩种" + gameId);
        return null;

    }
}

