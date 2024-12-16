package org.springframework.samples.petclinic.surgery.award;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.visit.Visit;

public class ReverseAwardAlgorithm implements AwardAlgorithm {

    // This implementation sorts only by severity and in ascending order (it should be descending!)
    // @Override
    // public Set<Disease> identifyDiseases(Set<Visit> visits) {
    //     if(visits==null || visits.isEmpty())
    //         return Set.of();
    //     // Buscamos cual es la severidad máxima entre las enfermedades detectadas:            
    //     Integer maxServerity=visits.stream().filter(v->v.getDiagnose()!=null).map((v)->v.getDiagnose().getSeverity()).distinct().min(Integer::compare).get();
    //     // Filtramos las visitas sin diagnóstico o que no corresponden con enfermedades de máxima gravedad:
    //     List<Visit> filteredVisits=visits.stream().filter(v->v.getDiagnose()!=null).filter(v->v.getDiagnose().getSeverity()==maxServerity).toList();
    //     // Calculamos cuantas visitas ha habido para cada enfermedad        
    //     return new HashSet<>(
    //         filteredVisits.stream().map(v->v.getDiagnose()).toList()
    //         );
    // }

     // This implementation sorts only by severity and in ascending order (it should be descending!)
   @Override
    public Set<Vet> selectAwardedVets(Set<Visit> visits) {
        if(visits==null || visits.isEmpty())
            return Set.of();

        Map<Object, Long> countVisits = visits.stream().map((v) -> v.getVet()).collect(Collectors.groupingBy(obj->obj, Collectors.counting()));
        Set<Vet> result = new HashSet<>();
        Optional<Long> maxVisits = countVisits.values().stream().min(Comparator.naturalOrder());
        maxVisits.ifPresent(max -> {
            countVisits.forEach((key, value) -> {
                if (value.equals(max)) {
                    result.add((Vet)key);
                }
            });
        });

        return result;    }
    
}
