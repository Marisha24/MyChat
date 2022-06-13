package com.example.test1.repository;

import com.example.test1.entity.Massage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MassageRepository extends PagingAndSortingRepository<Massage, Long> {
    Optional<Massage> findById(Long id);

}
