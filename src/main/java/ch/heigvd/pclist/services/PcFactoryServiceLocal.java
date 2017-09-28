package ch.heigvd.pclist.services;

import ch.heigvd.pclist.models.Pc;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PcFactoryServiceLocal {

    long pcBuilder(long cpuId, long ramId, long gpuId);

    List<Pc> getAllPcs();
}
