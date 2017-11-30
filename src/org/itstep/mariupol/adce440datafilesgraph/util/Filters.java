/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itstep.mariupol.adce440datafilesgraph.util;

import java.util.ArrayList;
import org.itstep.mariupol.adce440datafilesgraph.model.Channel;
import uk.me.berndporr.iirj.*;

/**
 *
 * @author student
 */
public class Filters {
    
    public static void doFilter(ArrayList<Channel> _channelsArrayList){
    
        //Butterworth butterworth = new Butterworth();
        ChebyshevI chebyshevI = new ChebyshevI();
        int order = 2;
        int Samplingfreq = _channelsArrayList.get(0).getmChannelFreq();
        System.out.println(Samplingfreq);
        int CutoffFrequ = 16;
        chebyshevI.lowPass(order, Samplingfreq, CutoffFrequ, 1);
        //butterworth.lowPass(order, Samplingfreq, CutoffFrequ);
        for (int i = 0; i < _channelsArrayList.size() - 1; i++) {
            
            for (int j = 0; j < _channelsArrayList.get(i).size() - 1; j++) {
                
                Double dataItem =
                        _channelsArrayList.get(i).getDataItem(j);
                //dataItem = butterworth.filter(dataItem);
                dataItem = chebyshevI.filter(dataItem);
                _channelsArrayList.get(i).setDataItem(j, dataItem);
            }
        }
    }
}
