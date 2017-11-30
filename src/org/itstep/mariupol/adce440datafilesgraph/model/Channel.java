package org.itstep.mariupol.adce440datafilesgraph.model;

import java.util.ArrayList;

public class Channel {
	
	//private String mFilePathString;
	@SuppressWarnings("unused")
	private final int mChannelNumber;
	@SuppressWarnings("unused")
	private final int mChannelAmp;
        @SuppressWarnings("unused")
        private final int mChannelFreq;
	private ArrayList<Double> mChannelDataArrayList;
	
	public Channel (int channeNlumber, int channelAmp, int сhannelFreq) {
		mChannelNumber = channeNlumber;
		mChannelAmp = channelAmp;
                mChannelFreq = сhannelFreq;
		mChannelDataArrayList = new ArrayList<>();
	}
	
	public int getChannelNumber() {
		return mChannelNumber;
	}

	public int getChannelAmp() {
		return mChannelAmp;
	}

        public int getmChannelFreq() {
            return mChannelFreq;
        }

	public void addDataItem (Double dataItemDouble) {
		mChannelDataArrayList.add(dataItemDouble);
	}
        
        public void setDataItem (int idx, Double dataItemDouble) {
		mChannelDataArrayList.set(idx, dataItemDouble);
	}

	public Double getDataItem (int dataItemPos) {
		return mChannelDataArrayList.get(dataItemPos);
	}

	public ArrayList<Double> getChannelDataArrayList() {
		return mChannelDataArrayList;
	}

	public int size() {
		return mChannelDataArrayList.size();
	}
}
