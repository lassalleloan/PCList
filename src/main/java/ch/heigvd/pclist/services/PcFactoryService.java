package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Pc;

import java.util.List;

public class PcFactoryService implements PcFactoryServiceLocal {
    @Override
    public long pcBuilder(long cpuId, long ramId, long gpuId) {
        return 0;
    }

    @Override
    public List<Pc> getAllPcs() {
        return null;
    }
}
