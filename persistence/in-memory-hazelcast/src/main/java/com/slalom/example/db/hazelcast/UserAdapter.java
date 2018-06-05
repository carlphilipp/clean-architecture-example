package com.slalom.example.db.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.slalom.example.core.entity.User;
import com.slalom.example.core.spi.UserRepository;
import com.slalom.example.db.hazelcast.model.UserDb;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// todo : Pourquoi dans le in memory simple tu as nommé la classe UserRepositorySimpleAdapter et ici juste UserAdapter ;)
// todo : en fonction de la lecture des todos j'ai laissé trainer autre part ce que je fais pour le nommage des adapters
// todo : [nom de l'implémentation] + [nom du port/interface] égale ici HazelCastUserRepository
public class UserAdapter implements UserRepository {

	private static final HazelcastInstance HAZELCAST = Hazelcast.getInstance();
	private static final String MAP_NAME = "user";

	@Override
	public User create(final User user) {
		var userDb = UserDb.from(user);
		var map = HAZELCAST.getMap(MAP_NAME);
		map.put(userDb.getId(), userDb);
		return user;
	}

	@Override
	public Optional<User> findById(final String id) {
		var map = HAZELCAST.<String, UserDb>getMap(MAP_NAME);
		if (map.containsKey(id)) {
			var userDb = map.get(id);
			return Optional.of(userDb.toUser());
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> findByEmail(final String email) {
		return HAZELCAST.<String, UserDb>getMap(MAP_NAME)
			.values().stream()
			.filter(userDb -> userDb.toUser().getEmail().equals(email))
			.map(UserDb::toUser)
			.findAny();
	}

	@Override
	public List<User> findAllUsers() {
		return HAZELCAST.<String, UserDb>getMap(MAP_NAME)
			.values()
			.stream()
			.map(UserDb::toUser)
			.collect(Collectors.toList());
	}
}
