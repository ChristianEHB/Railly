package com.example.naits.railly.model;

import com.example.naits.railly.util.DateUtil;

public class DepartureInfo {
	private long timeStamp;
	private PlatformInfo platform;
	private int delay;
	private boolean canceled;
	
	public DepartureInfo(long timeStamp, PlatformInfo platform, int delay, boolean canceled) {
		super();
		this.timeStamp = timeStamp;
		this.platform = platform;
		this.delay = delay;
		this.canceled = canceled;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public PlatformInfo getPlatform() {
		return platform;
	}

	public int getDelay() {
		return delay;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setPlatform(PlatformInfo platform) {
		this.platform = platform;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (canceled ? 1231 : 1237);
		result = prime * result + delay;
		result = prime * result + ((platform == null) ? 0 : platform.hashCode());
		result = prime * result + (int) (timeStamp ^ (timeStamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		DepartureInfo other = (DepartureInfo) obj;
		if (canceled != other.canceled) return false;
		if (delay != other.delay) return false;
		if (platform == null) {
			if (other.platform != null) return false;
		} else if (!platform.equals(other.platform)) return false;
		if (timeStamp != other.timeStamp) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "at " + DateUtil.timeStampToDate(timeStamp) + " on " + platform.toString() + " with " + delay + " delay(" + canceled + ")";
	}
}
