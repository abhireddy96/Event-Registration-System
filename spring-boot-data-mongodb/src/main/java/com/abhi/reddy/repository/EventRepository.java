package com.abhi.reddy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhi.reddy.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
	
	public Event findByEid(int eid);
	
	public int deleteByEid(int eid);

	@Query(value="{eid: {'$in': ?0}}", fields="{employees: 0}")
	public List<Event> findAllByEid(List<Integer> eventIds);

	public boolean existsByEid(int eventId);
	
	/*public List<Event> findAllByEidIn(List<Integer> eventIds);
*/

}
