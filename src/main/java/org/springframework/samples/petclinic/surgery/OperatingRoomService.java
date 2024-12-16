package org.springframework.samples.petclinic.surgery;

import java.util.List;
import java.util.Optional;

public class OperatingRoomService {
    private OperatingRoomRepository repo;

    public OperatingRoomService(OperatingRoomRepository tr){
        this.repo=tr;
    }

    public List<OperatingRoom> getAll() {
        return null;
    }

    public OperatingRoom save(OperatingRoom t) {
        return null;
    }
}
