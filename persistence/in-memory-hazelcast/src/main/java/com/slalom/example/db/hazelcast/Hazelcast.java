package com.slalom.example.db.hazelcast;

import com.hazelcast.core.HazelcastInstance;

class Hazelcast {

	private static final Object LOCK = new Object();
	private static HazelcastInstance HAZELCAST;

	static HazelcastInstance getInstance() {
		if (HAZELCAST == null) {
			synchronized (LOCK) {
				if (HAZELCAST == null) {
					HAZELCAST = com.hazelcast.core.Hazelcast.newHazelcastInstance();
				}
			}
		}
		return HAZELCAST;
	}

	private Hazelcast() {
	}
}
