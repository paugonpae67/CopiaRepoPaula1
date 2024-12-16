/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.pet.PetType;

public interface VetRepository extends CrudRepository<Vet, Integer> {

	@Query("SELECT DISTINCT vet FROM Vet vet WHERE vet.user.id = :userId")
	public Optional<Vet> findVetByUser(int userId);

	// STATS
	// ADMIN
	@Query("SELECT COUNT(v) FROM Vet v")
	public Integer countAll();

	@Query("SELECT NEW MAP(v.city as city, cast(COUNT(v) as string) as vets)" + " FROM Vet v GROUP BY v.city")
	public List<Map<String, String>> countVetsGroupedByCity();

	@Query("SELECT NEW MAP(v.vet.firstName as firstName, v.vet.lastName as lastName, cast(COUNT(v) as string) as visits)"
			+ " FROM Visit v GROUP BY v.vet")
	public List<Map<String, String>> countVisitsGroupedByVet();

	@Query("SELECT s FROM Specialty s WHERE s.name=?1")
	public Specialty findSpecialtyByName(String name);

	// This query is only to avoid problems in the previous tests. You don't have to/shouldn't
	// use it as a starting point for Test 6
	@Query("SELECT v FROM Vet v")
	public Set<Vet> findMoreActiveVets(@Param("start") LocalDateTime start, 
	                                    @Param("end") LocalDateTime end, 
										@Param("type") Set<PetType> type,
										@Param("threshold") int threshold);

}
