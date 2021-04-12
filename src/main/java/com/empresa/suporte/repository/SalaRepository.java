package com.empresa.suporte.repository;

import com.empresa.suporte.model.Sala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    public List<Sala> findBySemana(String semana);

    @Query(value=" SELECT h.id, h.hora, h.limite, h.semana, h.local, h.descricao, h.titulo "
    		+ "    FROM sala h"
    		+ "    WHERE h.semana = ?1 "
    		+ "    AND h.limite > ("
    		+ "     		SELECT COUNT(u.usuario_id)"
    		+ "	    		FROM sala ho "
    		+ "						LEFT JOIN usuario_sala u ON u.sala_id = ho.id "
    		+ "     		WHERE ho.semana = ?1 AND ho.id = h.id)"
    		+ "	  	ORDER BY h.hora", nativeQuery=true) 
    public List<Sala> findBySemanaAndLimite(String semana);
    
    
    @Query(value=" SELECT h.id, h.hora, h.limite, h.semana, h.local, h.descricao, h.titulo  "
    		+ "		   FROM sala h"
    		+ "    	   WHERE h.semana = ?1 "
    		+ "        AND h.limite > ("
    		+ "     			SELECT COUNT(u.usuario_id)"
    		+ "	    			FROM sala ho "
    		+ "							LEFT JOIN usuario_sala u ON u.sala_id = ho.id "
    		+ "     			WHERE ho.semana = ?1 AND ho.id = h.id AND u.usuario_id <> ?2)"
    		+ "	  		  ORDER BY h.hora", nativeQuery=true) public List<Sala> findBySemanaAndLimite(String semana, long usuario_id);
    
}
