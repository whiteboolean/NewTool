package com.mtool.toolslib.model.enumList;

/**
 * Created by JayCruz on 2017/10/2.
 */

public enum eu_LotteryCategory {

    SSC("SSC"), L11X5("11X5"), L3DP3("3DP3"), K3("K3"), PK10("PK10"), KLSF("KLSF"), KL8("KL8"), SUFT("SUFT");


    private String GameCode;

    eu_LotteryCategory(String GameCode) {
        this.GameCode = GameCode;
    }


}

