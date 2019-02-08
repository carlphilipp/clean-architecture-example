package com.slalom.example.db.hazelcast;

import com.hazelcast.core.HazelcastInstance;

class Hazelcast {

	private static class InstanceHolder {
		private static HazelcastInstance HAZELCAST = null;
	}

	private Hazelcast() {}

	static HazelcastInstance getInstance() {
		if (InstanceHolder.HAZELCAST == null) {
			InstanceHolder.HAZELCAST = com.hazelcast.core.Hazelcast.newHazelcastInstance();
		}
		return InstanceHolder.HAZELCAST;
	}
}
