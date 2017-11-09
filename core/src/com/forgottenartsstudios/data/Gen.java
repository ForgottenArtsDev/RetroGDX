package com.forgottenartsstudios.data;

import com.forgottenartsstudios.helpers.Variables;

/**
 * Created by forgo on 11/9/2017.
 */

public class Gen {
    public static void tempStats(int statNum) {
        Variables.usePoint = true;
        Variables.usePointTimer = 1;
        // Temp stats
        if (Variables.tempStats.Points > 0) {
            switch (statNum) {
                case 1:
                    Variables.tempStats.STR = Variables.tempStats.STR + 1;
                    Variables.tempStats.Points--;
                    break;
                case 2:
                    Variables.tempStats.DEF = Variables.tempStats.DEF + 1;
                    Variables.tempStats.Points--;
                    break;
                case 3:
                    Variables.tempStats.VIT = Variables.tempStats.VIT + 1;
                    Variables.tempStats.Points--;
                    break;
                case 4:
                    Variables.tempStats.AGI = Variables.tempStats.AGI + 1;
                    Variables.tempStats.Points--;
                    break;
                case 5:
                    Variables.tempStats.MAG = Variables.tempStats.MAG + 1;
                    Variables.tempStats.Points--;
                    break;
            }
        }
    }
}
